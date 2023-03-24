package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"github.com/otiai10/copy"
	"os"
)



func main(){
	var i int
	fmt.Println("Hello! What kind of project would you like to create?")
	fmt.Scanln(&i)
	switch i{
	case 1:
		fmt.Println("Enter the project name")
		python_discord()
	case 2:
		fmt.Println("Creating Java (discord base)")
		java_discord()
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