#include <iostream>
#include <vector>
#include <queue>
#include <fstream>
#include <string>
#include <sstream>
#include <conio.h>
using namespace std;
void processing_graph(string&, vector<string>& our_result), cont(), BreadthFirstSearch(int N, int vertex, vector<vector<int>>& graph, vector<string>& our_result);
void print_path(int i, int* path, string& add);
bool output_check(string, vector<string>&);

int main() {
	string graph_path;
	graph_path = "RR-1graphs/graphN.txt";
	vector<string> our_result;
	for (int i = 49; i <= 54; i++) {
		graph_path[16] = char(i);
		processing_graph(graph_path, our_result);
		output_check(graph_path, our_result) ? cout << "Answers are correct" << endl : cout << "Answers aren`t correct" << endl;
		our_result.clear();
		cont();
	}
}

void processing_graph(string& graph_path, vector<string>& our_result) {
	int N = 0, element_number = 0;
	bool unrelated_flag=false;
	string a;
	vector<vector<int>> graph;
	vector<int> temp, unrelated_vertex;
	string str, tmp;
	ifstream file;
	file.open(graph_path);
	while (!file.eof()) {
		getline(file, str);
		N++;
	}
	file.seekg(0, ios::beg);
	while (!file.eof()) {
		getline(file, str);
		istringstream tempstring(str);
		tempstring >> a;
		if (str.find(" ") == -1) { 
			unrelated_vertex.push_back(stoi(a));
			temp.push_back(stoi(a));
			graph.push_back(temp);
			temp.clear();
			continue;
		}
		while (tempstring >> a) {
			temp.push_back(stoi(a));
		}
		graph.push_back(temp);
		temp.clear();
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < unrelated_vertex.size(); j++) {
			if (i == unrelated_vertex[j]) {
				unrelated_flag = true;
			}
		}
		if (!unrelated_flag) {
			BreadthFirstSearch(N, i, graph, our_result);
			cout << "----" << endl;
		}
		else {
			cout << "Unrelated vertex" << endl << "----" << endl;
			our_result.push_back("Unrelated vertex");
		}
		unrelated_flag = false;
	}
	file.close(); 
}

void BreadthFirstSearch(int N,int vertex, vector<vector<int>> &graph, vector<string>& our_result) {
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
	string add;
	for (int i = 0; i < N; i++) {
		if (path[i]<0) {
			cout <<"Unrelated vertex - "<<i<<endl;
			add = "Unrelated vertex - " + to_string(i);
			our_result.push_back(add);
		}
		else {
			print_path(i, path, add);
			cout << endl;
			add.pop_back();
			our_result.push_back(add);
		}
		add = "";
	}
	delete[] burned;
	delete[] path;
}

void print_path(int i, int* path, string& add) {
	if (path[i] != i) {
		print_path(path[i], path, add);
	}
	cout << i << " ";
	add += to_string(i) + " ";
}

void cont() {
	cout << "Would you like to continue? [y/n] ";
	int x = _getch();
	if (x == 121) {
		system("cls");
	}
	else if(x == 110) {
		 exit(0);
	}
	else {
		cout << endl;
		cont();
	}
}

bool output_check(string expected, vector<string>& our_result) {
	string temp1;
	int N1 = 0;

	expected.erase(expected.end()-4,expected.end());
	expected += "expected.txt";
	ifstream expected_result;
	expected_result.open(expected);
	while (!expected_result.eof()) {
		getline(expected_result, temp1);
		N1++;
	}
	expected_result.seekg(0, ios::beg);
	if (N1 != our_result.size()) {
		return false;
	}
	else {
		for (int i = 0; i < our_result.size(); i++) {
			getline(expected_result, temp1);
			if (temp1 != our_result[i]) {
				expected_result.close();
				return false;
			}
		}
	}
	expected_result.close();
	return true;
}