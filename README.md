## CSC435 Programming Assignment 1 (Winter 2024)
**Jarvis College of Computing and Digital Media - DePaul University**

**Student**: Shubham Sawant (ssawant4@depaul.edu)

**Solution programming language**: JAVA
[The ReadMe Contents for the Code are Below the Requirements]

### Requirements

If you are implementing your solution in C++ you will need to have GCC 12.x and CMake 3.22.x installed on your system. On Ubuntu 22.04 you can install GCC and set it as default compiler using the following commands:

```
sudo apt install g++-12 gcc-12 cmake
sudo update-alternatives --remove-all gcc
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 110
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-12 120
sudo update-alternatives --remove-all g++
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-11 110
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-12 120
```

If you are implementing your solution in Java you will need to have Java 1.7.x and Maven 3.6.x installed on your systems. On Ubuntu 22.04 you can install Java and Maven using the following commands:

```
sudo apt install openjdk-17-jdk maven

```

### Setup

There are 5 datasets (Dataset1, Dataset2, Dataset3, Dataset4, Dataset5) that you need to use to evaluate your solution. Before you can evaluate your solution you need to download the datasets. You can download the datasets from the following link:

https://depauledu-my.sharepoint.com/:f:/g/personal/aorhean_depaul_edu/EgmxmSiWjpVMi8r6QHovyYIB-XWjqOmQwuINCd9N_Ppnug?e=TLBF4V

After you finished downloading the datasets copy them to the dataset directory (create the directory if it does not exist). Here is an example on how you can copy Dataset1 to the remote machine and how to unzip the dataset:

```
remote-computer$ cd <path-to-repo>
remote-computer$ mkdir datasets
local-computer$ scp Dataset1.zip cc@<remote-ip>:<path-to-repo>/datasets/.
remote-computer$ cd <path-to-repo>/datasets
remote-computer$ unzip Dataset1.zip
```

### C++ solution
#### How to build/compile

To build the C++ solution use the following commands:
```
cd app-cpp
mkdir build
cmake -S . -B build
cmake --build build
```

#### How to run application

To run the C++ clean dataset program (after you build the project) use the following command:
```
./build/clean_dataset <input directory> <output directory>
```

To run the C++ word count program (after you build the project) use the following command:
```
./build/count_words <input directory> <output directory>
```

To run the C++ sort words program (after you build the project) use the following command:
```
./build/sort_words <input directory> <output directory>
```

### Java solution
#### How to build/compile

To build the Java solution use the following commands:
```
cd app-java
mvn compile
mvn package
```

#### How to run application

To run the Java clean dataset program (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.CleanDataset <input directory> <output directory>
```

To run the Java word count program (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.CountWords <input directory> <output directory>
```

To run the Java sort_words program (after you build the project) use the following command:
```
java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.SortWords <input directory> <output directory>
```
##Code:

###SortWords
####Purpose: The 'SortWords' program is designed to traverse through a directory, read text files, sort the words within each file, and store the sorted content in new files. The output and input files maintain a similar directory structure.

####Implementation:
Data Structures: For handling file content, file path, and words, String, Path, and Arrays are utilized.
Sorting Algorithm: It leverages the Java standard library's natural order sorting for efficient sorting using lexicography.
Compute/Memory/IO Intensity: Primarily IO-intensive, reading, processing, and writing files. Moderate memory usage for individual file processing.

####Usage:
java SortWords input_directory output_directory
input_directory: Location of the directory path where the text files are to be sorted.
output_directory: Location of the directory path where the sorted files are to be sorted.

###CleanDataSet
####Purpose:
The CleanDataSet program cleans the contents of text files located in a specified input directory. The process eliminates unnecessary characters, replaces repeating delimiters, and saves the cleaned material in new files, all while maintaining the initial directory structure.

####Implementation:
Data Structures: For handling file content, file path, and words, String, Path, and Arrays are utilized.
Cleaning Algorithm: To remove unwanted characters and repeating delimiters, regular expressions are used.
Compute/Memory/IO Intensity: Mainly focused on I/O operations that involve reading, processing, and writing files. Optimize memory utilization for cleaning individual files.

####Usage:
java CleanDataSet input_directory output_directory
input_directory: Location of the directory path where the text files are to be cleaned.
output_directory: Location of the directory path where the cleaned files are to be sorted.

###CountWords
####Purpose:
The CountWords program determines the number of words in text files located in a specified input directory. The program saves the number of words in certain files located in an output directory while preserving the original folder structure.

####Implementation:
Data Structures: For handling file content, file path, and words, String, Path, and Arrays are utilized.
Counting Algorithm: To count the number of words in each file, stream processing is used.
Compute/Memory/IO Intensity: Mainly focused on I-O operations that involve reading, processing, and writing files. Individual file counting results in memory use that ranges from low to moderate.

####Usage:
java CountWords input_directory output_directory
input_directory: Location of the directory path where the text files are to be counted.
output_directory: Location of the directory path where the count files are to be sorted.
