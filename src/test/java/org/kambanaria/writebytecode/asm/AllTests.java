package org.kambanaria.writebytecode.asm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TrivialZombunnyTest.class, ZombunnyTest.class, RenameTest.class, AddFieldTest.class, AddInterfaceTest.class, ChangeMethodTest.class, ManipulateConstructorsTest.class})
public class AllTests {
}
