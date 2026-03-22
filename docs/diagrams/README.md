# UML Diagrams for Transaction Manager

This directory contains PlantUML source files for the UML diagrams used in the Developer Guide.

## Files

1. **ArchitectureOverview.puml** - High-level component architecture diagram
2. **ClassDiagram.puml** - Detailed class diagram showing all components and their relationships
3. **AddTransactionSequence.puml** - Sequence diagram for the `add transaction` command
4. **DeleteTransactionSequence.puml** - Sequence diagram for the `delete transaction` command  
5. **ApplicationStartupSequence.puml** - Sequence diagram showing application initialization

## How to Generate Images

### Using PlantUCLI (Command Line)
```bash
# Install PlantUML (requires Java)
# Generate PNG files
plantuml *.puml
```

### Using Online PlantUML Editor
1. Visit http://www.plantuml.com/plantuml
2. Copy the content of a `.puml` file
3. Paste into the online editor
4. Download the generated image as PNG

### Using VSCode Extension
1. Install "PlantUML" extension in VSCode
2. Open `.puml` file
3. Use `Alt+D` (Windows/Linux) or `Option+D` (Mac) to preview
4. Export as PNG from preview

## Diagram Conventions

- **Component Names**: Use PascalCase for class/components
- **Relationships**: Arrows indicate dependency/association
- **Notes**: Yellow notes provide explanations
- **Colors**: Light background (#EEEBDC) with white components (#FFF)
- **Formatting**: Consistent spacing and alignment for readability

## Adding New Diagrams

1. Create new `.puml` file in this directory
2. Follow existing formatting conventions
3. Update references in DeveloperGuide.md
4. Generate PNG images
5. Commit both `.puml` source and `.png` images