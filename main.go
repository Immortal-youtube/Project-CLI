package main
import "fmt"

func main(){
	var i int
	fmt.Println("Hello! What kind of project would you like to create?")
	fmt.Scanln(&i)
	switch i{
	case 1:
		fmt.Println("Creating Python(discord base)")
	case 2:
		fmt.Println("Creating Java (discord base)")
	}
}