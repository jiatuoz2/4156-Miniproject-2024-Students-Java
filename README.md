# Welcome Students of 4156

Please follow the assignment specifications on Courseworks when completing this project.

Bug finder - PMD

Command to find bug from the IndividualProject directory:

```bash
cd IndividualProject
alias pmd="./pmd-bin-7.5.0/bin/pmd"
pmd check -d ./src -R rulesets/java/quickstart.xml -f text
```
