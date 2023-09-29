function doGet() {
  var htmlOutput = HtmlService.createHtmlOutputFromFile('html');
  htmlOutput = htmlOutput.setTitle('Tanach Verse Search').setSandboxMode(HtmlService.SandboxMode.IFRAME);
  PropertiesService.getScriptProperties().setProperty("timesVisited", parseInt(PropertiesService.getScriptProperties().getProperty("timesVisited")) + 1);
  return htmlOutput;
}

function fetchVersesByKey(key) {
  console.log("key: " + key);
    var file = DriveApp.getFileById('1Hbg_hTxs0Y-UtWf2Nz269oRUHvZShRrN');
    var jsonContent = file.getBlob().getDataAsString();
    try {
        var jsonData = JSON.parse(jsonContent);
        if (key in jsonData) {
            return jsonData[key];
        } else {
            console.log("Key not found in the JSON data.");
            return null;
        }
    } catch (e) {
        console.log("Error parsing JSON data: " + e.toString());
        return null;
    }
}

function getIndex(firstLetter, lastLetter) {
  let index = (getHebrewAlphabetValue(firstLetter) * 22) + getHebrewAlphabetValue(lastLetter);
    return fetchVersesByKey(String (index));
}

function getHebrewAlphabetValue(letter) {
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
            return -1;
    }
}
