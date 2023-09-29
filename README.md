# Tanach Pasuk Search

Tanach Pasuk Search is a project that allows users to explore verses from the Tanach (Hebrew Bible) based on their first and last letters. This project uses a unique numbering system to categorize verses into 484 different categories, making it easy for users to access specific verses of interest.

## How it Works

1. **Data Preparation**: The project begins with a text file (tanach.txt) containing verses from the Tanach. These verses are sorted into 484 different categories using a custom numbering system (using TanachOrganizer.java and Verse.java). Each combination of the first letter and last letter is assigned a unique number.

2. **JSON Generation**: The categorized verses are then stored in a JSON file (tanach.json). The JSON file maps the unique number to the corresponding verses.

3. **User Interface**: A user-friendly web interface is created using Google Apps Script (pasukFinder.html). Users can select the first letter and last letter of the verse they are interested in.

4. **Verse Retrieval**: When a user selects specific letters and clicks the "Submit" button, the system (pasukFinder.gs) calculates the unique number using the same numbering system. It then retrieves the corresponding verses from the JSON file.

5. **Display**: The retrieved verses are displayed to the user, allowing them to explore and study specific passages from the Tanach.

## Try It Out

You can access the Tanach Pasuk Search tool by visiting the following link: [Tanach Pasuk Search](https://script.google.com/macros/s/AKfycbxX29YoD9gmOcLdVG1PHZYJbmukxMOd7PCnHBQvqMAUeURW8Ort5vOmaWIwF5nRRsjk/exec)

## Usage

- Select the first letter and last letter of the verse you want to explore.
- Click the "Submit" button to retrieve and display the verses.

## Contributing

Contributions to this project are welcome. Feel free to fork the repository, make improvements, and submit pull requests.
