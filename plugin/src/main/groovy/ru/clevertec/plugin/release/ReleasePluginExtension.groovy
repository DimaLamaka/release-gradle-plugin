package ru.clevertec.plugin.release

import org.gradle.api.provider.Property

interface ReleasePluginExtension {
    abstract Property<String> getReleaseBranch()
}