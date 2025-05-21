package dogrulukcesaret;

import java.util.*;

class SoruVeritabani {
    public static List<String> getDogrulukSorulari() {
        return Arrays.asList(
            "Hayatındaki en büyük pişmanlık nedir?",
            "Bir arkadaşına hiç yalan söyledin mi?",
            "Gizli bir yeteneğin var mı?",
            "Utandığın bir anıyı anlat."
        );
    }

    public static List<String> getCesaretSorulari() {
        return Arrays.asList(
            "En yakınındaki kişiye garip bir surat yap.",
            "Yüksek sesle bir şarkı söyle.",
            "Komik bir dans yap.",
            "Bir kişiyi arayıp şaka yap."
        );
    }
}