#!/bin/bash

# Script to generate PNG images from PlantUML files
# Note: Requires PlantUML to be installed

echo "Generating UML diagrams from PlantUML files..."

# Check if plantuml is installed
if ! command -v plantuml &> /dev/null; then
    echo "Error: PlantUML is not installed."
    echo "Please install PlantUML first:"
    echo "  - Download from https://plantuml.com/starting"
    echo "  - Or install via package manager:"
    echo "    - macOS: brew install plantuml"
    echo "    - Ubuntu/Debian: sudo apt-get install plantuml"
    echo "    - Windows: Download plantuml.jar"
    echo ""
    echo "Alternatively, use an online PlantUML editor at:"
    echo "  http://www.plantuml.com/plantuml"
    exit 1
fi

# Generate PNG files
echo "Generating PNG files..."
plantuml *.puml

# Check if generation was successful
if [ $? -eq 0 ]; then
    echo "Successfully generated PNG files:"
    ls -la *.png
else
    echo "Error generating PNG files."
    echo "Please check your PlantUML installation."
fi

echo ""
echo "To manually generate using Java (if plantuml.jar is installed):"
echo "  java -jar plantuml.jar *.puml"