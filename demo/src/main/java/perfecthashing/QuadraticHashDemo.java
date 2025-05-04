package perfecthashing;

import java.util.Arrays;
import java.util.HashSet;

import perfecthashing.Hashes.QuadraticHash;

public class QuadraticHashDemo {
    public static void main(String[] args) {
        String[] corpus = {
    "the", "be", "to", "of", "and", "a", "in", "that", "have", "I",
    "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
    "this", "but", "his", "by", "from", "they", "we", "say", "her", "she",
    "or", "an", "will", "my", "one", "all", "would", "there", "their", "what",
    "so", "up", "out", "if", "about", "who", "get", "which", "go", "me",
    "when", "make", "can", "like", "time", "no", "just", "him", "know", "take",
    "people", "into", "year", "your", "good", "some", "could", "them", "see", "other",
    "than", "then", "now", "look", "only", "come", "its", "over", "think", "also",
    "back", "after", "use", "two", "how", "our", "work", "first", "well", "way",
    "even", "new", "want", "because", "any", "these", "give", "day", "most", "us",
    "is", "are", "was", "were", "been", "being", "has", "had", "does", "did",
    "having", "doing", "shall", "should", "may", "might", "must", "can", "could", "would",
    "am", "become", "became", "seem", "seemed", "appear", "appeared", "feel", "felt", "sound",
    "sounded", "look", "looked", "smell", "smelled", "taste", "tasted", "remain", "remained", "stay",
    "stayed", "keep", "kept", "turn", "turned", "grow", "grew", "get", "got", "go",
    "went", "come", "came", "leave", "left", "arrive", "arrived", "return", "returned", "move",
    "moved", "run", "ran", "walk", "walked", "sit", "sat", "stand", "stood", "lie",
    "lay", "rise", "rose", "fall", "fell", "jump", "jumped", "climb", "climbed", "crawl",
    "crawled", "swim", "swam", "fly", "flew", "drive", "drove", "ride", "rode", "sail",
    "sailed", "float", "floated", "sink", "sank", "flow", "flowed", "drift", "drifted", "slide",
    "slid", "roll", "rolled", "spin", "spun", "turn", "turned", "twist", "twisted", "bend",
    "bent", "stretch", "stretched", "shrink", "shrank", "grow", "grew", "expand", "expanded", "contract",
    "contracted", "open", "opened", "close", "closed", "shut", "shut", "lock", "locked", "unlock",
    "unlocked", "start", "started", "begin", "began", "stop", "stopped", "end", "ended", "finish",
    "finished", "continue", "continued", "pause", "paused", "wait", "waited", "stay", "stayed", "remain",
    "remained", "live", "lived", "die", "died", "exist", "existed", "occur", "occurred", "happen",
    "happened", "take", "took", "get", "got", "give", "gave", "send", "sent", "receive",
    "received", "bring", "brought", "carry", "carried", "hold", "held", "keep", "kept", "put",
    "put", "place", "placed", "set", "set", "lay", "laid", "drop", "dropped", "throw",
    "threw", "catch", "caught", "pick", "picked", "choose", "chose", "select", "selected", "find",
    "found", "lose", "lost", "search", "searched", "look", "looked", "see", "saw", "watch",
    "watched", "observe", "observed", "notice", "noticed", "hear", "heard", "listen", "listened", "sound",
    "sounded", "speak", "spoke", "talk", "talked", "say", "said", "tell", "told", "ask",
    "asked", "answer", "answered", "reply", "replied", "respond", "responded", "shout", "shouted", "yell",
    "yelled", "scream", "screamed", "whisper", "whispered", "call", "called", "name", "named", "mention",
    "mentioned", "discuss", "discussed", "explain", "explained", "describe", "described", "report", "reported", "announce",
    "announced", "state", "stated", "declare", "declared", "proclaim", "proclaimed", "say", "said", "speak",
    "spoke", "talk", "talked", "tell", "told", "ask", "asked", "answer", "answered", "reply",
    "replied", "respond", "responded", "shout", "shouted", "yell", "yelled", "scream", "screamed", "whisper",
    "whispered", "call", "called", "name", "named", "mention", "mentioned", "discuss", "discussed", "explain",
    "explained", "describe", "described", "report", "reported", "announce", "announced", "state", "stated", "declare",
    "declared", "proclaim", "proclaimed", "say", "said", "speak", "spoke", "talk", "talked", "tell",
    "told", "ask", "asked", "answer", "answered", "reply", "replied", "respond", "responded", "shout",
    "shouted", "yell", "yelled", "scream", "screamed", "whisper", "whispered", "call", "called", "name",
    "named", "mention", "mentioned", "discuss", "discussed", "explain", "explained", "describe", "described", "report",
    "reported", "announce", "announced", "state", "stated", "declare", "declared", "proclaim", "proclaimed", "say",
    "said", "speak", "spoke", "talk", "talked", "tell", "told", "ask", "asked", "answer",
    "answered", "reply", "replied", "respond", "responded", "shout", "shouted", "yell", "yelled", "scream",
    "screamed", "whisper", "whispered", "call", "called", "name", "named", "mention", "mentioned", "discuss",
    "discussed", "explain", "explained", "describe", "described", "report", "reported", "announce", "announced", "state",
    "stated", "declare", "declared", "proclaim", "proclaimed", "say", "said", "speak", "spoke", "talk",
    "talked", "tell", "told", "ask", "asked", "answer", "answered", "reply", "replied", "respond",
    "responded", "shout", "shouted", "yell", "yelled", "scream", "screamed", "whisper", "whispered", "call",
    "called", "name", "named", "mention", "mentioned", "discuss", "discussed", "explain", "explained", "describe",
    "described", "report", "reported", "announce", "announced", "state", "stated", "declare", "declared", "proclaim",
    "proclaimed", "say", "said", "speak", "spoke", "talk", "talked", "tell", "told", "ask",
    "asked", "answer", "answered", "reply", "replied", "respond", "responded", "shout", "shouted", "yell"
        };
        boolean similar = false;
        corpus = Arrays.stream(corpus).distinct().toArray(String[]::new);
        HashSet<Integer> set = new HashSet<>();
        for (String word : corpus)
        {
            Integer hash = word.hashCode();
            if (set.contains(hash))
            {
                similar = true;
                break;
            }
            set.add(hash);
        }
        System.out.println(similar);
        QuadraticHash hashTable = new QuadraticHash();
        for (String word : corpus)
        {
            hashTable.insert(word);
        }
        hashTable.display();
        System.out.println(hashTable.getSize());
        System.err.println(hashTable.getRehashes());
        for (int i = 0 ; i < corpus.length ; i+=2)
        {
            hashTable.delete(corpus[i]);
        }
        hashTable.display();
        System.out.println(hashTable.getSize());

    }
}
