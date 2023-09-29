import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TanachOrganizer {
    public static void main(String[] args) {
        ArrayList<Verse>[] sections = new ArrayList[484];
        for (int i = 0; i < sections.length; i++) {
            sections[i] = new ArrayList<>();
        }
        try {
            File file = new File("C:\\Users\\DEN - NEW\\IdeaProjects\\pauskFinder\\lib\\tanach.txt");
            Scanner fileScanner = new Scanner(file);
            fileScanner.useDelimiter("\\.");
            Verse currentVerse;
            String currentBook = "";
            String line;
            while (fileScanner.hasNext()) {
                line = fileScanner.next().replaceAll("[\\u000D\\n]", "");
                if (line.contains("^")) {
                    break;
                }
                while (line.charAt(0) == '\u0020') {
                    line = line.substring(1);
                }
                if (line.contains("[")) {
                    currentBook = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    continue;
                }
                currentVerse = new Verse(line, currentBook);
                sections[getIndex(currentVerse.getFirstLetter(), currentVerse.getLastLetter())].add(currentVerse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        createJSON(sections);
//        findPasuk(sections);
    }

    public static void createJSON(ArrayList<Verse>[] sections) {
        String jsonStr = "{" + System.lineSeparator();
        for (int x = 0; x < sections.length; x++) {
            jsonStr += "\t\"" + x + "\": [";
            for (int i = 0; i < sections[x].size(); i++) {
                jsonStr += "\"" + sections[x].get(i).getData() + " (" + sections[x].get(i).getSource() + ")\"";
                if (i != (sections[x].size() - 1)) {
                    jsonStr += ", ";
                }
            }
            if (x != (sections.length) - 1) {
                jsonStr += "]," + System.lineSeparator();
            }
            else {
                jsonStr += "]" + System.lineSeparator();
            }
        }
        jsonStr += "}";
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + File.separator + "out" + File.separator + "tanach.json";
        System.out.println("filepath: " + filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonStr);
            System.out.println("Successfully created JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing JSON data to file: " + e.getMessage());
        }
    }

    public static void findPasuk(ArrayList<Verse>[] sections) {
        int index;
        Scanner userInput = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 0 - 483 (Enter \"-1\" to exit)");
            index = userInput.nextInt();
            if (index == -1) {
                System.exit(0);
            }
            System.out.println("sections[index].size(): " + sections[index].size());
            for (int i = 0; i < sections[index].size(); i++) {
                System.out.println(sections[index].get(i).getData() + " (" + sections[index].get(i).getSource() + ")");
            }
        }
    }

    public static int getIndex(char firstLetter, char lastLetter) {
        return (getHebrewAlphabetValue(firstLetter) * 22) + getHebrewAlphabetValue(lastLetter);
    }

    public static int getHebrewAlphabetValue(char letter) {
        switch (letter) {
            case 'א':
                return 0;
            case 'ב':
                return 1;
            case 'ג':
                return 2;
            case 'ד':
                return 3;
            case 'ה':
                return 4;
            case 'ו':
                return 5;
            case 'ז':
                return 6;
            case 'ח':
                return 7;
            case 'ט':
                return 8;
            case 'י':
                return 9;
            case 'כ':
            case 'ך':
                return 10;
            case 'ל':
                return 11;
            case 'מ':
            case 'ם':
                return 12;
            case 'נ':
            case 'ן':
                return 13;
            case 'ס':
                return 14;
            case 'ע':
                return 15;
            case 'פ':
            case 'ף':
                return 16;
            case 'צ':
            case 'ץ':
                return 17;
            case 'ק':
                return 18;
            case 'ר':
                return 19;
            case 'ש':
                return 20;
            case 'ת':
                return 21;
            default:
                return -1; // Return -1 for characters outside the Hebrew alphabet
        }
    }
}
