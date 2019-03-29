#!/bin/bash

# Change this to match your repository root
readonly TEAM_REPO="https://bitbucket.org/te-cle-cohort-10"

echo
read -r -p "Enter your name (First Last)? " name
read -r -p "Enter your email? " email
read -r -p "Are you in Java or C#? " cohort

reponame=${name//[[:blank:]]/}

if [ "$cohort" == "java" ] || [ "$cohort" == "Java" ]; then
	cohort="java"
else
	cohort="c"
fi	

echo
echo "Setting Up Global Configuration Settings"

git config --global user.name "${name}"
git config --global user.email "${email}"

echo "Setting up Git Editors and Tools..."

git config --global core.editor "code -w -n"
git config --global diff.tool code
git config --global difftool.code.cmd "code -w -d \$LOCAL \$REMOTE"

echo
echo "Cloning Repositories..."

git clone "${TEAM_REPO}/${reponame}-${cohort}"

echo
echo "Configuring Upstream..."

cd "${reponame}-${cohort}"
git remote add upstream "${TEAM_REPO}/${cohort}-main"
git config branch.master.mergeOptions "--no-edit"

echo "Done."