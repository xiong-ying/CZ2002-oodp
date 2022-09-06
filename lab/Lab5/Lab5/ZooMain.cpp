#include "animal.h"
#include "childAnimal.h"

std::map<COLOR, std::string> color_names{{ Green, "Green"}, { Blue, "Blue"}, { White, "White"}, { Black, "Black"}, { Brown, "Brown"} };

// Animal class implementation
Animal::Animal(): _name("unknown"), _color(Black) {
	cout << "constructing Animal object " << _name << endl;
}

Animal::Animal(string n, COLOR c) {
	_name = n;
	cout << "constructing Animal object " << _name << " with color " << color_names[c] << endl;
}

Animal::~Animal() {
	cout << "destructing Animal object " << _name << endl;
}

// Mammal class implementation
Mammal::Mammal(string n, COLOR c):Animal(n, c) {}

Mammal::~Mammal() {
	cout << "destructing Mammal object " << endl;
}

void Mammal::eat() {
	cout << "It eats" << endl;
}

void Mammal::move() {
	cout << "It moves" << endl;
}


// Dog class implementation
Dog::Dog(string n, COLOR c, string o) :Mammal(n, c) {
	dogOwner = o;
	cout << "Dog Owner is " << dogOwner << endl;
}

Dog::~Dog() {
	cout << "destructing Dog object " << endl;
}

void Dog::speak() {
	cout << "Woof" << endl;
}

void Dog::move() {
	cout << "Dog moves" << endl;
}

// Cat class implementation
Cat::Cat(string n, COLOR c):Mammal(n, c) {
}

Cat::~Cat() {
	cout << "destructing Cat object " << endl;
}

void Cat::speak() {
	cout << "Meow" << endl;
}

void Cat::move() {
	cout << "Cat moves" << endl;
}

// Lion class implementation
Lion::Lion(string n, COLOR c):Mammal(n, c) {

}

Lion::~Lion() {
	cout << "destructing Lion object " << endl;
}

void Lion::speak() {
	cout << "Roar" << endl;
}

void Lion::move() {
	cout << "Lion moves" << endl;
}


int main() {

	Dog dog1("Lassie", White, "Andy");
	Cat cat1("Kitty", Black);
	Lion lion1("bigKitty", Green);

	Mammal* arrAnimal[3];
	arrAnimal[0] = &dog1;
	arrAnimal[1] = &cat1;
	arrAnimal[2] = &lion1;


	int select;
	cout << "Select the animal to send to Zoo:" << endl;
	cout << "(1)Dog (2)Cat (3)Lion (4)Move all animals (5)Quit" << endl;
	cin >> select;

	if (select == 1 || select == 2 || select == 3) {
		arrAnimal[select - 1]->move();
		arrAnimal[select - 1]->speak();
		arrAnimal[select - 1]->eat();
	}
	else if (select == 4) {
		for (int i = 0; i < 3; i++) {
			arrAnimal[i]->move();
			arrAnimal[i]->speak();
			arrAnimal[i]->eat();
		}
	}
	else {
		cout << "Invalid Input." << endl;
	}

	cout << "Program exiting бн. " << endl;
	return 0;
}