package main
import (
	// "log"
	"os"
	"fmt"
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
	
	os.Mkdir("C:/Users/anshs/OneDrive/Documents/Code/Python/" + projectname,os.ModePerm)
	os.Mkdir("C:/Users/anshs/OneDrive/Documents/Code/Python/" + projectname + "/commands",os.ModePerm)
	os.Mkdir("C:/Users/anshs/OneDrive/Documents/Code/Python/" + projectname + "/listeners",os.ModePerm)
	os.Create("C:/Users/anshs/OneDrive/Documents/Code/Python/" + projectname + "/main.py")
	os.Create("C:/Users/anshs/OneDrive/Documents/Code/Python/" + projectname + "/token.env")
	

}
func java_discord(){
	fmt.Println("Project has been setup")
}