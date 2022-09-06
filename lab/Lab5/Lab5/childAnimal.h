#ifndef CHILDANIMAL_h
#define CHILDANIMAL_h

#include "animal.h"

class Dog : public Mammal {

public:
	Dog(string n, COLOR c, string o);
	~Dog();

	void speak();
	void move();

private:
	string dogOwner;
};


class Cat : public Mammal {

public:
	Cat(string n, COLOR c);
	~Cat();

	void speak();
	void move();
};

class Lion : public Mammal {

public:
	Lion(string n, COLOR c);
	~Lion();

	void speak();
	void move();
};

#endif