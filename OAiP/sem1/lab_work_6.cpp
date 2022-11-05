#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
	setlocale(LC_ALL, "ru");
	int a = 0, temp=0;
	string str1, str2;
	vector<int> vec;
	cout << "Введите числа через пробел: ";
	getline(cin, str1);
	for (int i = 0; i < str1.size(); i++) {
		if (int(str1[i]) == 32) {
			str2+=str1[i];
			while (int(str1[i]) == 32) {
				i += 1;
			}
			str2 += str1[i];
		}
		else {
			str2 += str1[i];
		}
	}
	for (unsigned int i = 0; i < str2.size(); i++) {
		if (int(str2[i])==32) {
			vec.push_back(a);
			a = 0;
		}
		else {
			a = a * 10 + (int(str2[i]) - 48);
		}
	}
	vec.push_back(a);
	for (int i = 0; i < vec.size()-1; i++) {
		for (int j = 0; j < vec.size()-1; j++) {
			if (vec[j] > vec[j + 1]) {
				temp = vec[j];
				vec[j] = vec[j + 1];
				vec[j + 1] = temp;
			}
		}
	}
	for (int i = 0; i < vec.size(); i++) {
		cout <<vec[i]<<" ";
	}
}