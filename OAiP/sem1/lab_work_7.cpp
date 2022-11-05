#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;
void ReadInfo(), ShowInfo(), func(), AddInfo(), RemoveInfo(), EditInfo(), FileRewrite(), SortingStudents1(), SortingStudents2(), Solution();
int FindPosition(string str);

int main() {
	setlocale(LC_ALL, "ru");
	func();
}

void func() {
	int x = 0;
	cout << "1 - вывести информацию о студентах\n2 - добавить студента\n3 - удалить студента\n4 - редактировать информацию о студенте\n5 - cортировка по ср.баллу\n6 - cортировка по алфавиту\n7 - решение индивидуального задания\n0 - закрыть программу\n";
	cin >> x;
	switch (x) {
	case 1:
		ReadInfo();
		break;
	case 2:
		AddInfo();
		break;
	case 3:
		RemoveInfo();
		break;
	case 4:
		EditInfo();
		break;
	case 5:
		SortingStudents1();
		break;
	case 6:
		SortingStudents2();
		break;
	case 7:
		Solution();
		break;
	case 0:
		break;
	default:
		func();
		break;
	}
}

struct students {
	string name;
	int group;
	double physics, math, informatics, av_sc;
};

students st[30];
int Num;

void ReadInfo() {
	system("cls");
	ifstream file("AverageStudentScore.txt");
	int i = 0;
	while (!file.eof()) {
		file >> st[i].name >> st[i].group >> st[i].physics >> st[i].math >> st[i].informatics >> st[i].av_sc;
		i++;
	}
	Num = i - 1;
	ShowInfo();
}

void ShowInfo() {
	cout << left << setw(16) << "Имя:" << setw(10) << "Группа:" << setw(15) << "Физика:" << setw(15) << "Математика:" << setw(15) << "Информатика:" << setw(15) << "Средняя оценка: "<<endl;
	for (int i = 0; i < Num; i++) {
		cout <<left<< setw(16) << st[i].name << setw(10)<< st[i].group << fixed<<setw(15)<<setprecision(2) << st[i].physics << fixed << setw(15) << setprecision(2) << st[i].math<< fixed << setw(15) << setprecision(2) << fixed << setw(15) << setprecision(2) <<st[i].informatics<< fixed << setw(15) << setprecision(2) <<st[i].av_sc << endl;
	}
	func();
}

void AddInfo() {
	system("cls");
	ofstream file("AverageStudentScore.txt", ios::app);
	double average_mark;
	Num++;
	cout << "Введите имя: ";
	cin >> st[Num - 1].name;
	cout << "Введите номер группы: ";
	cin >> st[Num - 1].group;
	cout << "Введите оценку по физике: ";
	cin >> st[Num - 1].physics;
	cout << "Введите оценку по математике: ";
	cin >> st[Num - 1].math;
	cout << "Введите оценку по информатике: ";
	cin >> st[Num - 1].informatics;
	average_mark = (st[Num-1].physics+st[Num-1].math+st[Num-1].informatics)/3;
	file << st[Num - 1].name << " " << st[Num-1].group<<" "<<st[Num-1].physics<<" "<<st[Num-1].math<<" "<<st[Num-1].informatics<<" "<<average_mark << endl;
	file.close();
	func();
}

int FindPosition(string str) {
	for (int i = 0; i < Num; i++) {
		if (st[i].name == str) {
			return i;
		}
	}
	cout << "Нет такого студента" << endl;
	return -1;
}

void RemoveInfo() {
	system("cls");
	string temp;
	cout << "Введите имя студента для удаления: ";
	cin >> temp;
	int pos = FindPosition(temp);
	if (pos == -1) {
		func();
	}
	else {
		for (int i = pos; i < Num - 1; i++) {
			st[i] = st[i + 1];
		}
	}
	Num--;
	FileRewrite();
}

void FileRewrite() {
	ofstream file("AverageStudentScore.txt");
	for (int i = 0; i < Num; i++) {
		file << st[i].name <<" "<< st[i].group<<" "<< st[i].physics<<" " << st[i].math<<" "<< st[i].informatics<<" "<< st[i].av_sc << endl;
	}
	func();
}

void EditInfo() {
	system("cls");
	string temp;
	double average_mark;
	cout << "Введите имя студента для редактирования: ";
	cin >> temp;
	int pos = FindPosition(temp);
	if (pos == -1) {
		func();
	}
	else {
		cout << "Введите новое имя студента: ";
		cin >> st[pos].name;
		cout << "Введите новую группу студента: ";
		cin >> st[pos].group;
		cout << "Введите новую оценку по физике студента: ";
		cin >> st[pos].physics;
		cout << "Введите новую оценку по математике студента: ";
		cin >> st[pos].math;
		cout << "Введите новую оценку по информатике студента: ";
		cin >> st[pos].informatics;
		average_mark = (st[pos].physics + st[pos].math + st[pos].informatics) / 3;
		st[pos].av_sc = average_mark;
	}
	FileRewrite();
}

void SortingStudents1() {
	system("cls");
	for (int i = 0; i < Num; i++) {
		for(int j = 0; j<Num - 1; j++){
			if (st[j].av_sc>st[j+1].av_sc) {
				swap(st[j], st[j+1]);
			}
		}
	}
	FileRewrite();
}

void SortingStudents2() {
	system("cls");
	for (int i = 0; i < Num; i++) {
		for (int j = 0; j < Num - 1; j++) {
			if (st[j].name > st[j + 1].name) {
				swap(st[j], st[j + 1]);
			}
		}
	}
	FileRewrite();
}

void Solution(){
	system("cls");
	double ov_av_sc = 0;
	for (int i = 0; i < Num; i++) {
		ov_av_sc += st[i].av_sc;
	}
	ov_av_sc = ov_av_sc / Num;
	cout << "Общий средний балл: "<<ov_av_sc<<endl;
	for (int i = 0; i < Num; i++) {
		if (st[i].av_sc >= ov_av_sc) {
			cout << st[i].name << endl;
		}
	}
	func();
}