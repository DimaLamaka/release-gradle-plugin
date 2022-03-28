package ru.clevertec.plugin.release.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import ru.clevertec.plugin.release.GitUtils

class CreateMajorRelease extends DefaultTask {


    @TaskAction
    def createMajorRelease() {

        def tags = GitUtils.getGitTagsResult()
        println(tags)

        def tagsArray = tags.split("\n")
        tagsArray.toList().forEach { println it }

        def currentVersion = tagsArray[tagsArray.size() - 1]
        println("current version = $currentVersion")

        def currentVersionSplitted = currentVersion.split('\\.')

        def newMajorVersion = Integer.parseInt(currentVersionSplitted[0].replaceAll("[^\\d.]", "")) + 1

        def newVersion = String.join(".", "v".concat(newMajorVersion as String), "0")
        println("new version = $newVersion")

        GitUtils.createTag(newVersion)
        ("git push origin $newVersion").execute()
    }

}