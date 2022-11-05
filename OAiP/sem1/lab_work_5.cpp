#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

int main() {
	setlocale(LC_ALL, "ru");
	int n = 0, m = 0, max_element = -2147483647, temp=0;
	vector<int> maximum, tempvec;
	cout << "Введите n и m: ";
	cin >> n >> m;
	int** matrix = new int*[n];
	for (int i = 0; i < n; i++) {
		matrix[i] = new int [m];
		cout << "Строка №" << i + 1 << endl;
		for (int j = 0; j < m; j++) {
			cout << "Введите элемент " << i + 1 << "-" << j + 1 << ": ";
			cin >> matrix[i][j];
			max_element = max(max_element, matrix[i][j]);
		}
		maximum.push_back(max_element);
		max_element = -2147483647;
	}
	for (int i = 0; i < n-1; i++) {
		if (maximum[i] > maximum[i + 1]) {
			temp = maximum[i];
			maximum[i] = maximum[i + 1];
			maximum[i + 1] = temp;
			for (int j = 0; j < m; j++) {
				tempvec.push_back(matrix[i][j]);
				matrix[i][j] = matrix[i + 1][j];
				matrix[i + 1][j] = tempvec[j];
			}
			tempvec.clear();
			i = -1;
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << matrix[i][j] << " ";
		}
		cout << endl;
	}
}