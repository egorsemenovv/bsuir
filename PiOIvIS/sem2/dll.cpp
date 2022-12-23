template <typename Type>

class DoublyLinkedList {

private:

	struct Node {
		Type item;
		Node* prev;
		Node* next;
	};

	Node* first;
	Node* last;

	bool is_empty();

public:

	DoublyLinkedList();

	~DoublyLinkedList();

	void push_back(Type);
};

template <typename Type>
DoublyLinkedList<Type>::DoublyLinkedList() {

}

template <typename Type>
DoublyLinkedList<Type>::~DoublyLinkedList() {

}

template <typename Type>
bool DoublyLinkedList<Type>::is_empty() {
	return first == nullptr;
}

template <typename Type>
void DoublyLinkedList<Type>::push_back(Type _item) {
	Node* temp = new Node(_item);
	if (is_empty()) {
		first = temp;
		last = temp;
		return;
	}
	last->next = temp;
	last = temp;
}