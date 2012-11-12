package org.kambanaria.writebytecode.compiler;

public class SourceStrings {

    private SourceStrings() {
    }
    public final static String I = "                                         "
            + "import java.util.Random;                                      "
            + "public class I {                                              "
            + "    public boolean singOutOfTune() {                          "
            + "        return new Random().nextBoolean();                    "
            + "    }                                                         "
            + "}                                                             ";
    public final static String YOU = "                                       "
            + "public class You {                                            "
            + "    public static String DID = \"StandUp&WalkOutOnMe\";       "
            + "    public static String DIDNOT = \"LendMeAnEar\";            "
            + "    public String wouldDo(boolean iF) {                       "
            + "        return iF ? \"StandUp&WalkOutOnMe\" : \"LendMeAnEar\";"
            + "    }                                                         "
            + "}                                                             ";
}
