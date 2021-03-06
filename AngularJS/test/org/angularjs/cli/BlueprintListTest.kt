package org.angularjs.cli

import com.intellij.testFramework.LightPlatformTestCase
import com.intellij.util.containers.ContainerUtil
import junit.framework.TestCase
import java.util.*

/**
 * @author Dennis.Ushakov
 */
class BlueprintListTest : LightPlatformTestCase() {
  fun testList() {
    val output = DEFAULT_OUTPUT
    val blueprints = BlueprintParser().parse(output)
    val requiredBlueprints = Arrays.asList("class", "component", "module", "service")
    val existingBlueprints = ContainerUtil.filter(blueprints) { requiredBlueprints.contains(it.name) }
    TestCase.assertEquals(requiredBlueprints.size, existingBlueprints.size)
    TestCase.assertEquals(listOf("--flat", "--inline-template", "--inline-style", "--prefix", "--spec", "--view-encapsulation",
                                 "--change-detection", "--skip-import", "--module", "--export", "--app"),
                          existingBlueprints[1].options.map { "--" + it.name })

    val blacklistedBlueprints = Arrays.asList("aliases:")
    val nonBlueprints = ContainerUtil.filter(blueprints) { blacklistedBlueprints.contains(it.name) }
    TestCase.assertEquals(0, nonBlueprints.size)
  }

  fun testNewList() {
    val output = "Available schematics:\n" +
                 "    application\n" +
                 "    class\n" +
                 "    component\n" +
                 "    directive\n" +
                 "    enum\n" +
                 "    guard\n" +
                 "    interface\n" +
                 "    module\n" +
                 "    pipe\n" +
                 "    service\n"
    val blueprints = BlueprintParser().parse(output)
    val requiredBlueprints = Arrays.asList("class", "component", "module", "service")
    val existingBlueprints = ContainerUtil.filter(blueprints) { requiredBlueprints.contains(it.name) }
    TestCase.assertEquals(requiredBlueprints.size, existingBlueprints.size)
  }

}
