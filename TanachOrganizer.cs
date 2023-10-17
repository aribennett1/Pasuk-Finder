using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace ConsoleApplication1
{
    public class TanachOrganizer
    {
        public static void Main(string[] args)
        {
            List<Verse>[] sections = new List<Verse>[484];
            for (var i = 0; i < sections.Length; i++)
            {
                sections[i] = new List<Verse>();
            }
            try
            {
                var tanachStr = File.ReadAllText(Path.Combine(Environment.CurrentDirectory, "lib", "tanach.txt"));
                var tanach = tanachStr.Split('.');
                    string currentBook = string.Empty;
                    for (var i = 0; i < tanach.Length; i++)
                    {
                        if (tanach[i].Contains("^"))
                        {
                            break;
                        }
                        while (tanach[i][0] == '\u0020')
                        {
                            tanach[i] = tanach[i].Substring(1);
                        }
                        if (tanach[i].Contains("["))
                        {
                            currentBook = tanach[i].Substring(tanach[i].IndexOf("[") + 1, tanach[i].IndexOf("]") - tanach[i].IndexOf("[") - 1);
                            continue;
                        }
                        Verse currentVerse = new Verse(tanach[i], currentBook);
                        sections[GetIndex(currentVerse.GetFirstLetter(), currentVerse.GetLastLetter())].Add(currentVerse);
                    }
            }
            catch (IOException e)
            {
                Console.WriteLine(e);
            }
            // CreateJSON(sections);
            FindPasuk(sections);
        }
        
        private static void CreateJSON(List<Verse>[] sections)
    {
        var jsonStr = new StringBuilder("{" + Environment.NewLine);
        for (var x = 0; x < sections.Length; x++)
        {
            jsonStr.Append($"\t\"{x}\": [");
            for (var i = 0; i < sections[x].Count; i++)
            {
                jsonStr.Append($"\"{sections[x][i].Data} ({sections[x][i].Source})\"");
                if (i != sections[x].Count - 1)
                {
                    jsonStr.Append(", ");
                }
            }
            jsonStr.Append(x != sections.Length - 1 ? "]," + Environment.NewLine : "]" + Environment.NewLine);
        }
        jsonStr.Append("}");
        var currentDirectory = Environment.CurrentDirectory;
        var filePath = Path.Combine(currentDirectory, "out", "tanach.json");
        Console.WriteLine("filepath: " + filePath);
        try
        {
            File.WriteAllText(filePath, jsonStr.ToString());
            Console.WriteLine("Successfully created JSON file.");
        }
        catch (IOException e)
        {
            Console.WriteLine("Error writing JSON data to file: " + e.Message);
        }
    }

    private static void FindPasuk(List<Verse>[] sections)
    {
        int index;
        while (true)
        {
            Console.WriteLine("Enter 0 - 483 (Enter \"-1\" to exit)");
            index = int.Parse(Console.ReadLine());
            if (index == -1)
            {
                Environment.Exit(0);
            }
            Console.WriteLine($"sections[{index}].Count: {sections[index].Count}");
            foreach (var verse in sections[index])
            {
                Console.WriteLine($"{verse.Data} ({verse.Source})");
            }
        }
    }

    private static int GetIndex(char firstLetter, char lastLetter)
    {
        return (GetHebrewAlphabetValue(firstLetter) * 22) + GetHebrewAlphabetValue(lastLetter);
    }

    private static int GetHebrewAlphabetValue(char letter)
    {
        switch (letter)
        {
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
}
