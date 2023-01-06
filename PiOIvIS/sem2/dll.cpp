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

	Node& findElement(int) const;

public:

	DoublyLinkedList();
	DoublyLinkedList(Type);
	DoublyLinkedList(const DoublyLinkedList<Type>&);
	~DoublyLinkedList();

	void push_back(const Type&);
	void push_front(const Type&);
	DoublyLinkedList<Type>& add(const Type&, int);
	DoublyLinkedList<Type>& add(const Type[], int, int);
	DoublyLinkedList<Type>& add(const DoublyLinkedList<Type>&, int);
	DoublyLinkedList<Type>& add(const DoublyLinkedList<Type>&);
	void erase(int);

	int Size();
	int findElement_beg(Type);
	int findElement_end(Type);

	void print();
	void print_back();


	Type& operator[](int);
	Type operator[](int) const;
	const DoublyLinkedList<Type>& operator=(const DoublyLinkedList<Type>&);
	bool operator==(const DoublyLinkedList<Type>&);
	bool operator!=(const DoublyLinkedList<Type>&);

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

template<typename Type>
DoublyLinkedList<Type>::DoublyLinkedList(const DoublyLinkedList<Type>& obj) {
	*this = obj;
}

template <typename Type>
DoublyLinkedList<Type>::~DoublyLinkedList() {
	DoublyLinkedList<Type>::Node* temp = first;
	DoublyLinkedList<Type>::Node* temp1;
	while (temp) {
		temp1 = temp;
		temp = temp->next;
		delete temp1;
	}
	first = last = nullptr;
}

template <typename Type>
bool DoublyLinkedList<Type>::is_empty() {
	return first == nullptr;
}

template <typename Type>
typename DoublyLinkedList<Type>::Node& DoublyLinkedList<Type>::findElement(int pos) const {
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
void DoublyLinkedList<Type>::push_back(const Type& item) {
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
void DoublyLinkedList<Type>::push_front(const Type& item) {
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
DoublyLinkedList<Type>& DoublyLinkedList<Type>::add(const Type& item, int pos) {
	DoublyLinkedList<Type>::Node* temp = new DoublyLinkedList<Type>::Node;
	temp->item = item;
	if (is_empty()) {
		first = last = temp;
		size += 1;
		return *this;
	}
	if (pos >= size) {
		last->next = temp;
		temp->prev = last;
		last = temp;
		size += 1;
		return *this;
	}
	if (pos <= 0) {
		first->prev = temp;
		temp->next = first;
		first = temp;
		size += 1;
		return *this;
	}
	if (0 < pos < size) {
		temp->next = &findElement(pos);
		temp->prev = &findElement(pos - 1);
		temp->next->prev = temp;
		temp->prev->next = temp;
		size += 1;
		return *this;
	}
	size += 1;
	return *this;
}

template <typename Type>
DoublyLinkedList<Type>& DoublyLinkedList<Type>::add(const Type item[], int number, int pos) {
	for (int i = 0; i < number; i++, pos++)
	{
		add(item[i], pos);
	}
	return *this;
}
template <typename Type>
DoublyLinkedList<Type>& DoublyLinkedList<Type>::add(const DoublyLinkedList<Type>& obj, int pos) {
	for (int i = 0; i < obj.size; i++, pos++)
	{
		add(obj[i], pos);
	}
	return *this;
}

template<typename Type>
DoublyLinkedList<Type>& DoublyLinkedList<Type>::add(const DoublyLinkedList<Type>& obj) {
	return add(obj, size);
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

template<typename Type>
Type DoublyLinkedList<Type>::operator[](int i) const {
	if (i < 0 || i >= size)
		throw exception("List index out of bounds");
	return findElement(i).item;
}

template<typename Type>
const DoublyLinkedList<Type>& DoublyLinkedList<Type>::operator=(const DoublyLinkedList<Type>& obj) {

	if (!is_empty())
		this->~DoublyLinkedList();

	first = last = nullptr;

	add(obj);

	return obj;
}
template <typename Type>
bool DoublyLinkedList<Type>::operator==(const DoublyLinkedList<Type>& obj) {
	if (this->size != obj.size)
		return false;
	for (int i = 0; i < size; i++) {
		if ((*this)[i] != obj[i])
			return false;
	}
	return true;
}

template <typename Type>
bool DoublyLinkedList<Type>::operator!=(const DoublyLinkedList<Type>& obj) {
	if (this->size != obj.size)
		return true;
	for (int i = 0; i < size; i++) {
		if ((*this)[i] != obj[i])
			return true;
	}
	return false;
}

int main() {
	DoublyLinkedList<int> a;
	int arr[5] = { 2,3,4,5,6 };
	a.add(arr, 5, 1);
	DoublyLinkedList<int> b(a);
	b != a ? cout << "Yes" : cout << "No";
}