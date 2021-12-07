# CS-F213-Chess

## Running the code

<br />

### Clone the repo
```bash
git clone https://github.com/AnirudhM1/CS-F213-Chess.git
```

### cd into the folder
```bash
cd CS-F213-Chess.git
```

### Run the gui
```bash
ant run
```
This compiles and creates a jar which can be used to run the project.
<br />
<br />

### Alternatively, the jar file can be downloaded and used to run the program. Few configurations must be done for it to work properly:
<br />

```bash
# Create a directory to contain the assets and jar
mkdir chess

# Enter the directory
cd chess

# Create a folder (assets) to store the images of the pieces
mkdir assets

# Add all the images of the all the pieces here. All the images must be labelled with 2 characters.
# The first (W|B) denoting the color
# The second (K|N|R|Q|B|P) denoting the piece.
# ALl the images must be in png format

# The assets folder from this repo can be used as an example

# Download the jar file from https://github.com/AnirudhM1/CS-F213-Chess/releases/tag/v1
wget https://github.com/AnirudhM1/CS-F213-Chess/releases/download/v1/Chess.jar

# Run the jar file
java -jar Chess.jar
```