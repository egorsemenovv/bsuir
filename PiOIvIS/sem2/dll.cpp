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

	bool is_empty();

public:

	DoublyLinkedList();
	DoublyLinkedList(Type);
	~DoublyLinkedList();

	void push_back(Type);
	void push_front(Type);

	void print();
	void print_back();

};

template <typename Type>
DoublyLinkedList<Type>::DoublyLinkedList() {
	first = nullptr;
	last = nullptr;
}

template <typename Type>
DoublyLinkedList<Type>::DoublyLinkedList(Type item)
{
	first = last = new DoublyLinkedList<Type>::Node;
	first->prev = nullptr;
	first->next = nullptr;
	first->item = item;
}

template <typename Type>
DoublyLinkedList<Type>::~DoublyLinkedList() {

}

template <typename Type>
bool DoublyLinkedList<Type>::is_empty() {
	return first == nullptr;
}

template <typename Type>
void DoublyLinkedList<Type>::push_back(Type item) {
	DoublyLinkedList<Type>::Node* temp = new DoublyLinkedList<Type>::Node;
	temp->item = item;
	if (is_empty()) {
		first = last = temp;
		return;
	}
	temp->prev = last;
	last->next = temp;
	last = temp;
}

template <typename Type>
void DoublyLinkedList<Type>::push_front(Type item) {
	DoublyLinkedList<Type>::Node* temp = new DoublyLinkedList<Type>::Node;
	temp->item = item;
	if (is_empty()) {
		first = last = temp;
		return;
	}
	temp->next = first;
	first->prev = temp;
	first = temp;
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

int main() {
	DoublyLinkedList<int> a;
	a.push_back(3);
	a.push_front(2);
	a.push_back(4);
	a.push_front(1);
	a.print();
	a.print_back();
	return 0;
}