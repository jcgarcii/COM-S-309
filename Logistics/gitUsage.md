# Git Usage and Tutorial
Git is a very strong tool and learning how to use it properly take time and practice. The basic project structure is normally a *main* branch that that has several other branches that the development is done on so the *main* branch can stay clean. Once a *dev* branch is ready to be used it can be merged onto the *main* branch and all the commit history will travel with it. Commits are the most important part of git history. They can help developers track down where a feature was or see how the project is begin structured.

A commit template is very helpful for project with multiple editors or even just developing on your own to help keep a structed and readable git history. A commit template is full custoizable and along with many of gits other configs. We will edit a few of these configs to set up the commit template for this project. Many comapines will have basic commit structures and a company wide syntax that they use that will help other developers share and work on the same project.

#### Commit Syntax
- **Commit Message**
    - Summarizes changes in around 50 characters or less
    - Capitalize the subject line
    - Do not end the subject line with a period
    - Use the imperative mood in the subject line [^1]
    - Separate subject from summary with a blank line

- **Commit Tag**
    - Helps to sort commits and track down changes to certain features
    - Make sure to use Tags that already exist and not make new ones if possible

- **Commit Summary**
    - More detailed explanatory text
    - Wrap the body at 72 characters
    - Use the body to explain what and why vs. how

- **Issues**
    - Creates a reference to issues with an issue tracker
    - GitLab has an issue tracker

For more infromation of formatting a commit visit https://chris.beams.io/posts/git-commit/

#### Setup Commit Message
1. To begin make sure you have Git Bash installed on Windows. [^2]
2. Ensure you have the *main* branch checked out
    - To get the the correct branch `git checkout main`
3. In the project directory run `git config commit.message PROJ_DIR/Logistics/.gitmessage`
4. In the project directory run `git config core.editor EDITOR --wait`
    - You can use whatever editor you want `vim`, `nano`, `code` etc.
5. You are now able to commit using the template!


#### Example Commit
```
# ============== Commit Message ================ # <----- 50 character ruler
frontend: user: Add basic game lobby screen

# ========================= Commit Summary =========================== # <-- 72 character ruler
Added a link from the login screen to the user game lobby screen. When
the user selects the "Play Game" button from the menu they are
naviagted to a this page that shows the current plays in the lobby
along with those players game stats.

# Tagged Issues
Resolves: #12
See also: #456, #789
```
[^1]: Imperative mood just means “spoken or written as if giving a command or instruction”
[^2]: Feel free to use your perfered command console to preform git actions
