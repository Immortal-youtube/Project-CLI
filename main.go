package main

import (
	"fmt"
	"github.com/otiai10/copy"
)



func main(){
	var i int
	fmt.Println("Hello! What kind of project would you like to create?")
	fmt.Println("1.Python Discord \n2.Java Discord \n3.Java Minecraft")
	fmt.Scanln(&i)
	switch i{
	case 1:
		fmt.Println("Enter the project name")
		python_discord()
	case 2:
		fmt.Println("Enter the project name")
		java_discord()
	case 3:
		fmt.Println("Enter the project name")
		java_discord()
	case 4:
		fmt.Println("Enter the project name")
	}
	
}

func python_discord(){
	var projectname string
	fmt.Scanln(&projectname)
	copy.Copy("pythonDiscordTemplate","C:/Users/anshs/OneDrive/Documents/Code/Python/" + projectname)
	fmt.Println("Make sure to set the environment variables")
}
func java_discord(){
	var projectname string
	fmt.Scanln(&projectname)
	copy.Copy("JavaDiscordTemplate","C:/Users/anshs/OneDrive/Documents/Code/Java/" + projectname)
	fmt.Println("Change the Artifact ID in pom.xml")
}

func java_minecraft(){
	var projectname string
	fmt.Scanln(&projectname)
	copy.Copy("MinecraftTemplate","C:/Users/anshs/OneDrive/Documents/Code/Java/" + projectname)
	fmt.Println("Change version and Artifact Id in pom.xml")
}

func react_website(){
	var projectname string
	fmt.Scanln(&projectname)
	copy.Copy("reactjs","C:/Users/anshs/OneDrive/Documents/Code/WebDevelopement/" + projectname)
	fmt.Println("Good luck")
}