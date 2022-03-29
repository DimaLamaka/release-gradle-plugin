package ru.clevertec.plugin.release.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import ru.clevertec.plugin.release.GitUtils

class CreateMinorRelease extends DefaultTask {

    @TaskAction
    def createMinorRelease() {
        def tags = GitUtils.getGitTagsResult()
        if(tags.isEmpty()){
            println("Tags is empty. Create new tag v1.0")
            GitUtils.createTag("v1.0")
            ("git push origin v1.0").execute()
            return
        }
        println("tags = $tags")


        def tagsArray = tags.split("\n")
        tagsArray.toList().forEach { println it }

        def currentVersion = tagsArray[tagsArray.size() - 1]
        println("current version = $currentVersion")

        def currentVersionSplitted = currentVersion.split('\\.')
        println("splitted current version = $currentVersionSplitted")

        def currentMinor = Integer.parseInt(currentVersionSplitted[1]) + 1
        def newVersion = String.join(".", currentVersionSplitted[0], currentMinor as String)
        println("new version = $newVersion")

        GitUtils.createTag(newVersion)

        ("git push origin $newVersion").execute()
    }


}