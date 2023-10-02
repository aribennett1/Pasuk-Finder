public class Verse {
    String data;
    String source;

    public Verse(String str, String book) {
        String tempSourceStr = "";
        int endIndex = str.indexOf(" ");
        if (book.equals("שמואל") || book.equals("מלכים") || book.equals("דברי הימים") || book.equals("עזרא / נחמיה")) {
            int firstSpacePos = str.indexOf(' ');
            endIndex = str.indexOf(' ', firstSpacePos + 1);
        }
        tempSourceStr = book + " " + str.substring(0, endIndex);
        this.source = tempSourceStr.replaceAll("[\\s\\n\\t-]", " ").replaceAll("\\s+", " ");
        this.data = str.substring(str.indexOf(" ")).replaceAll("[,;:]", "").replaceAll("[\\s\\n\\t-]", " ").replaceAll("\\s+", " ");
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
