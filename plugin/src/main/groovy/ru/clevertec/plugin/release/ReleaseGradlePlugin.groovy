/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package ru.clevertec.plugin.release

import org.gradle.api.Project
import org.gradle.api.Plugin
import ru.clevertec.plugin.release.tasks.CheckByLastCommit
import ru.clevertec.plugin.release.tasks.CheckGitInSystem
import ru.clevertec.plugin.release.tasks.CheckGitStatus
import ru.clevertec.plugin.release.tasks.CreateMajorRelease
import ru.clevertec.plugin.release.tasks.CreateMinorRelease

class ReleaseGradlePlugin implements Plugin<Project> {
    void apply(Project project) {

        project.tasks.register("checkGitInSystem", CheckGitInSystem) {
            setGroup("release")
        }

        project.tasks.register("checkGitStatus", CheckGitStatus) {
            dependsOn("checkGitInSystem")
            setGroup("release")

        }
        project.tasks.register("checkByLastCommit", CheckByLastCommit) {
            dependsOn("checkGitStatus")
            setGroup("release")
        }

        project.tasks.register("createMinorRelease", CreateMinorRelease) {
            dependsOn("checkByLastCommit")
            setGroup("release")
        }

        project.tasks.register("createMajorRelease", CreateMajorRelease) {
            dependsOn("checkByLastCommit")
            setGroup("release")
        }
        /*def extension = project.extensions.create("releaseConfig", ReleasePluginExtension)*/
        ReleasePluginExtension extension = project.getExtensions().create("releaseConfig", ReleasePluginExtension)
        project.tasks.register("release") {
            def releaseBranch = extension.getReleaseBranch().convention("master")

            setGroup("release")
            if (GitUtils.currentBranch.trim() == releaseBranch) {
                dependsOn('createMajorRelease')
            } else {
                dependsOn('createMinorRelease')
            }
        }
    }
}

