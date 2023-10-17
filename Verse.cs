namespace ConsoleApplication1
{
    public class Verse
    {
        public string Data { get; }
        public string Source { get; }

        public Verse(string str, string book)
        {
            var endIndex = str.IndexOf(" ");
            if (book == "שמואל" || book == "מלכים" || book == "דברי הימים" || book == "עזרא / נחמיה")
            {
                var firstSpacePos = str.IndexOf(' ');
                endIndex = str.IndexOf(' ', firstSpacePos + 1);
            }
            var tempSourceStr = book + " " + str.Substring(0, endIndex);
            Source = System.Text.RegularExpressions.Regex.Replace(tempSourceStr, @"[\s\n\t-]", " ").Replace(" ", string.Empty);
            Data = System.Text.RegularExpressions.Regex.Replace(str.Substring(str.IndexOf(" ")), "[,;:]", "").Replace(" ", string.Empty);
        }

        public char GetFirstLetter()
        {
            var index = 0;
            while (char.ConvertToUtf32(Data, index).ToString("X4") == "0020")
            {
                index++;
            }
            return Data[index];
        }

        public char GetLastLetter()
        {
            return Data[Data.Length - 1];
        }
    }
}
