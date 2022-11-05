#include <iostream>
#include <vector>
#include <queue>
#include <fstream>
#include <string>
using namespace std;
void BreadthFirstSearch(int N, int vertex, vector<vector<int>>& graph);
void print_path(int i, int* path);
int main() {
	setlocale(LC_ALL, "ru");
	int N = 0, a=0, unrealated_flag=0, element_number=0;
	string graph_path = "C:/Users/Егор/OneDrive/Рабочий стол/PiOIvIS/RR-1graphs/graph1.txt", str;
	ifstream file;
	file.open(graph_path);
	if (!file.is_open()) {
		cout << "Такого файла нет!" << endl;
		return 0;
	}
	else {
		while (!file.eof()) {
			getline(file, str);
			N++;
		}
	}
	file.seekg(0, ios::beg);
	vector<vector<int>> graph;
	vector<int> temp, unrelated_vertex;
	for (int i = 0; i < N; i++) {
		getline(file, str);
		element_number = 0;
		for (int j = 0; j < str.size(); j++) {
			if (int(str[j]) == 32) {
				if (element_number != 0) {
					temp.push_back(a);
					a = 0;
				}
				a = 0;
				element_number += 1;
			}
			else {
				a = a * 10 + (int(str[j]) - 48);
			}
		}
		if (element_number == 0) {
			unrelated_vertex.push_back(i);
		}
		temp.push_back(a);
		a = 0;
		graph.push_back(temp);
		temp.clear();
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < unrelated_vertex.size(); j++){
			if (i == unrelated_vertex[j]) {
				unrealated_flag = 1;
			}
		}
		if (unrealated_flag != 1) {
			BreadthFirstSearch(N, i, graph);
			cout << "----" << endl;
		}
		else {
			cout << "Несвязанная вершина" << endl<<"----"<<endl;
		}
		unrealated_flag = 0;
	}
}

void BreadthFirstSearch(int N,int vertex, vector<vector<int>> &graph) {
	bool* burned = new bool[N];
	int* path = new int[N];
	for (int i = 0; i < N; i++) {
		burned[i] = false;
		path[i] = -1;
	}
	path[vertex] = vertex;
	burned[vertex] = true;
	queue<int> q;
	q.push(vertex);
	while (!q.empty()) {
		int vertex = q.front();
		q.pop();
		for (int i = 0; i < graph[vertex].size(); i++) {
			int neighbor = graph[vertex][i];
			if (!burned[neighbor]) {
				burned[neighbor] = true;
				path[neighbor] = vertex;
				q.push(neighbor);
			}
		}
	}
	for (int i = 0; i < N; i++) {
		if (path[i]<0) {
			cout <<"Несвязанная вершина - "<<i<<endl;
		}
		else {
			print_path(i, path);
			cout << endl;
		}
	}
	delete[] burned;
	delete[] path;
}

void print_path(int i, int* path) {
	if (path[i] != i) {
		print_path(path[i], path);
	}
	cout << i << " ";
}