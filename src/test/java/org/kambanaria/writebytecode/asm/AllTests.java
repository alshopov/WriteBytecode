package org.kambanaria.writebytecode.asm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TrivialZombunnyTest.class, ZombunnyTest.class, RenameTest.class, AddFieldTest.class, AddInterfaceTest.class, ManipulateConstructors.class, ChangeMethodTest.class})
public class AllTests {
}
