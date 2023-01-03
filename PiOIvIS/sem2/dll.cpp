#include <iostream>
using namespace std;

template <typename Type>

class DoublyLinkedList {

private:

	struct Node {
		Type item;
		Node* prev = nullptr;
		Node* next = nullptr;
	};

	Node* first;
	Node* last;

	int size;

	bool is_empty();

	Node& findElement(int);

public:

	DoublyLinkedList();
	DoublyLinkedList(Type);
	~DoublyLinkedList();

	void push_back(Type);
	void push_front(Type);
	void add(Type, int);
	void erase(int);

	int Size();
	int findElement_beg(Type);
	int findElement_end(Type);

	void print();
	void print_back();


	Type& operator[](int);

};

template <typename Type>
DoublyLinkedList<Type>::DoublyLinkedList() {
	first = nullptr;
	last = nullptr;
	size = 0;
}

template <typename Type>
DoublyLinkedList<Type>::DoublyLinkedList(Type item)
{
	first = last = new DoublyLinkedList<Type>::Node;
	first->prev = nullptr;
	first->next = nullptr;
	first->item = item;
	size = 1;
}

template <typename Type>
DoublyLinkedList<Type>::~DoublyLinkedList() {

}

template <typename Type>
bool DoublyLinkedList<Type>::is_empty() {
	return first == nullptr;
}
template <typename Type>
typename DoublyLinkedList<Type>::Node& DoublyLinkedList<Type>::findElement(int pos) {
	DoublyLinkedList<Type>::Node* current = first;
	if (pos <= 0)
		return *first;
	if (pos >= size - 1)
		return *last;
	for (int i = 1; i <= pos; i++) {
		current = current->next;
	}
	return *current;
}

template <typename Type>
void DoublyLinkedList<Type>::push_back(Type item) {
	DoublyLinkedList<Type>::Node* temp = new DoublyLinkedList<Type>::Node;
	temp->item = item;
	if (is_empty()) {
		first = last = temp;
		size += 1;
		return;
	}
	temp->prev = last;
	last->next = temp;
	last = temp;
	size += 1;
}

template <typename Type>
void DoublyLinkedList<Type>::push_front(Type item) {
	DoublyLinkedList<Type>::Node* temp = new DoublyLinkedList<Type>::Node;
	temp->item = item;
	if (is_empty()) {
		first = last = temp;
		size += 1;
		return;
	}
	temp->next = first;
	first->prev = temp;
	first = temp;
	size += 1;
}
template <typename Type>
void DoublyLinkedList<Type>::add(Type item, int pos) {
	DoublyLinkedList<Type>::Node* temp = new DoublyLinkedList<Type>::Node;
	temp->item = item;
	if (pos >= size) {
		temp->prev = last;
		last->next = temp;
		last = temp;
		size += 1;
		return;
	}
	if (pos <= 0) {
		temp->next = first;
		first->prev = temp;
		first = temp;
		size += 1;
		return;
	}
	temp->next = &findElement(pos);
	temp->prev = &findElement(pos - 1);
	(temp->next)->prev = temp;
	(temp->prev)->next = temp;
	size += 1;
}

template <typename Type>
int DoublyLinkedList<Type>::findElement_beg(Type item) {
	DoublyLinkedList<Type>::Node* temp = first;
	int current = 0;
	while (temp) {
		if (temp->item == item) {
			return current;
		}
		temp = temp->next;
		current += 1;
	}
	return -1;
}

template <typename Type>
int DoublyLinkedList<Type>::findElement_end(Type item) {
	DoublyLinkedList<Type>::Node* temp = last;
	int current = size - 1;;
	while (temp) {
		if (temp->item == item) {
			return current;
		}
		temp = temp->prev;
		current -= 1;
	}
	return -1;
}

template <typename Type>
void DoublyLinkedList<Type>::erase(int pos) {
	DoublyLinkedList<Type>::Node* temp = &findElement(pos);
	if (size == 1) {
		first = nullptr;
		last = nullptr;
		delete temp;
		size = 0;
		return;
	}
	if (pos <= 0) {
		(temp->next)->prev = nullptr;
		first = temp->next;
		size -= 1;
		delete temp;
		return;
	}
	if (pos >= size - 1) {
		(temp->prev)->next = nullptr;
		last = temp->prev;
		size -= 1;
		delete temp;
		return;
	}
	(temp->prev)->next = temp->next;
	(temp->next)->prev = temp->prev;
	size -= 1;
	delete temp;
}

template <typename Type>
int DoublyLinkedList<Type>::Size() {
	return size;
}

template <typename Type>
void DoublyLinkedList<Type>::print() {
	DoublyLinkedList<Type>::Node* temp = first;
	if (is_empty()) return;
	while (temp) {
		cout << temp->item << ' ';
		temp = temp->next;
	}
	cout << endl;
}

template <typename Type>
void DoublyLinkedList<Type>::print_back() {
	DoublyLinkedList<Type>::Node* temp = last;
	if (is_empty()) return;
	while (temp) {
		cout << temp->item << ' ';
		temp = temp->prev;
	}
	cout << endl;
}

template<typename Type>
Type& DoublyLinkedList<Type>::operator[](int i) {
	if (i < 0 || i >= size)
		throw exception("List index out of bounds");
	return findElement(i).item;
}

int main() {
	DoublyLinkedList<int> a;
	a.push_back(1);
	a.push_back(2);
	a.push_back(3);
	a.push_back(4);
	a.push_back(5);
	a.print();
	a.erase(2);
	a.print();
	a.print_back();
	return 0;
}