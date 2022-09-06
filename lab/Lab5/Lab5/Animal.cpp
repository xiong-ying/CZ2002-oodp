#include <iostream>
#include <map>
#include <string>
using namespace std;
enum COLOR { Green, Blue, White, Black, Brown };

std::map<COLOR, std::string> color_names{ { Green, "Green"}, { Blue, "Blue"}, { White, "White"}, { Black, "Black"}, { Brown, "Brown"} };


class Animal {
public:
	Animal() : _name("unknown") {
		cout << "constructing Animal object " << _name << endl;
	}

	Animal(string n, COLOR c) {
		_name = n;
		cout << "constructing Animal object " << _name << " with color " << color_names[c] << endl;
	}

	~Animal() {
		cout << "destructing Animal object " << _name << endl;
	}

	virtual void speak() {
		cout << "Animal speaks " << endl;
	}

	virtual void move() = 0;

private:
	string _name;
	COLOR _color;
};

class Mammal : public Animal {

public:

	Mammal(){}

	Mammal(string n, COLOR c) :Animal(n, c) {
	}

	~Mammal() {
		cout << "destructing Mammal object " << endl;
	}

	void eat() const {
		cout << "It eats" << endl;
	}

	void move() {
		cout << "It moves" << endl;
	}

};

class Dog : public Mammal {

public:

	Dog(string n, COLOR c, string o) :Mammal(n, c) {
		dogOwner = o;
		cout << "Owner is " << dogOwner << endl;
	}

	~Dog() {
		cout << "destructing Dog object " << endl;
	}

	void speak() {
		cout << "Woof" << endl;
	}

	void move() {
		cout << "Dog moves" << endl;
	}

private:
	string dogOwner;
};


class Cat : public Mammal {

public:

	Cat(string n, COLOR c) :Mammal(n, c) {

	}

	~Cat() {
		cout << "destructing Cat object " << endl;
	}

	void speak() {
		cout << "Meow" << endl;
	}

	void move() {
		cout << "Cat moves" << endl;
	}

};

class Lion : public Mammal {

public:

	Lion(string n, COLOR c) :Mammal(n, c) {

	}

	~Lion() {
		cout << "destructing Lion object " << endl;
	}

	void speak() {
		cout << "Roar" << endl;
	}

	void move() {
		cout << "Lion moves" << endl;
	}

};


int main() {
	/*
	Animal a;
	Animal a("tiger", White);
	a.speak();
	*/

	/*
	Mammal b("mammal", Brown);
	b.eat();
	b.speak();
	b.move();

	Dog c("dog", Green, "Molly");
	c.speak();
	c.move();
	*/

	/*
	Animal* animalPtr = new Dog("Lassie", White, "Andy");
	animalPtr->speak();
	animalPtr->move();
	*/

	/*
	Dog dogi("Lassie", White, "Andy");
	Mammal* aniPtr = &dogi;
	Mammal& aniRef = dogi;
	Mammal aniVal = dogi;

	aniPtr->speak();
	aniRef.speak();
	aniVal.speak();
	*/


	Dog dog1("Lassie", White, "Andy");
	Cat cat1("Kitty", Black);
	Lion lion1("bigKitty", Green);

	Mammal *arrAnimal[3];
	arrAnimal[0] = &dog1;
	arrAnimal[1] = &cat1;
	arrAnimal[2] = &lion1;


	int select;
	cout << "Select the animal to send to Zoo:" << endl;
	cout << "(1)Dog (2)Cat (3)Lion (4)Move all animals (5)Quit" << endl;
	cin >> select ;

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