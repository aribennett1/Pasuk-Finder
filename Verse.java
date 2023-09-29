public class Verse {
    String data;
    String source;

    public Verse(String str, String book) {
        if (book.equals("שמואל") || book.equals("מלכים") || book.equals("דברי הימים") || book.equals("עזרא / נחמיה")) {
            int firstSpacePos = str.indexOf(' ');
            int secondSpacePos = str.indexOf(' ', firstSpacePos + 1);
            this.source = book + " " + str.substring(0, secondSpacePos);
        } else {
            this.source = book + " " + str.substring(0, str.indexOf(" "));
        }
        this.data = str.substring(str.indexOf(" ")).replaceAll("[,;:\\n]", "").replace("-", " ").replaceAll("[ \\t]+", " ");
    }

    public String getData() {
        return data;
    }

    public String getSource() {
        return source;
    }

    public char getFirstLetter() {
        int index = 0;
        while (Integer.toHexString(getData().charAt(index)).equals("20")) {
            index++;
        }
        return getData().charAt(index);
    }

    public char getLastLetter() {
        String data = getData();
        return data.charAt(data.length() - 1);
    }
}
