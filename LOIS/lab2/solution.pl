% Лабораторная работа №2 по дисциплине "Логические основы интеллектуальных систем"
% Выполнена студентом группы 221703 БГУИР:
% - Семеновым Егором Геннадьевичем
% Вариант: 2, последнее изменение: 20.05.2024

% Данный файл является исходным кодом к программе, решающий следующую задачу:

% Два береги реки. На одном из них человек, который несёт капусту, ведёт козу и пойманного волка.
% Требуется с помощью лодки, вмещающей вместе с человеком не более одного животного или предмета,
% переправиться на другой берег. Человек не может оставлять козу с капустой и волка с козой.


% Проверка входных данных
check(Human, Cabbage, Goat, Wolf) :-
    ((Human == left; Human == right); (Cabbage == left; Cabbage == right); (Goat == left; Goat == right); (Wolf == left; Wolf == right)),
    ((Human == left, Goat == left); (Human == right, Goat \= Wolf, Goat \= Cabbage);
     (Human == right, Goat == right); (Human == left, Goat \= Wolf, Goat \= Cabbage)).

% Возможные переходы:
move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage, Goat, Wolf], 'Human cross river from left to right') :-
    % человек переплывает реку слева направо
    Human2 = right,
    Human = left,
    check(Human2, Cabbage, Goat, Wolf).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage, Goat, Wolf], 'Human cross river from right to left') :-
    % человек переплывает реку справа налево
    Human2 = left,
    Human = right,
    check(Human2, Cabbage, Goat, Wolf).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage2, Goat, Wolf], 'Human and Cabbage cross river from left to right') :-
    % человек и капуста переплывают реку слева направо
    Human2 = right,
    Human = left,
    Cabbage2 = right,
    Cabbage = left,
    check(Human2, Cabbage2, Goat, Wolf).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage2, Goat, Wolf], 'Human and Cabbage cross river from right to left') :-
    % человек и капуста переплывают реку справа налево
    Human2 = left,
    Human = right,
    Cabbage2 = left,
    Cabbage = right,
    check(Human2, Cabbage2, Goat, Wolf).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage, Goat2, Wolf], 'Human and Goat cross river from left to right') :-
    % человек и коза переплывают реку слева направо
    Human2 = right,
    Human = left,
    Goat2 = right,
    Goat = left,
    check(Human2, Cabbage, Goat2, Wolf).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage, Goat2, Wolf], 'Human and Goat cross river from right to left') :-
    % человек и коза переплывают реку справа налево
    Human2 = left,
    Human = right,
    Goat2 = left,
    Goat = right,
    check(Human2, Cabbage, Goat2, Wolf).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage, Goat, Wolf2], 'Human and Wolf cross river from left to right') :-
    % человек и волк переплывают реку слева направо
    Human2 = right,
    Human = left,
    Wolf2 = right,
    Wolf = left,
    check(Human2, Cabbage, Goat, Wolf2).

move([Human, Cabbage, Goat, Wolf], [Human2, Cabbage, Goat, Wolf2], 'Human and Wolf cross river from right to left') :-
    % человек и волк переплывают реку справа налево
    Human2 = left,
    Human = right,
    Wolf2 = left,
    Wolf = right,
    check(Human2, Cabbage, Goat, Wolf2).

% Поиск решения
% Конец решения, текущее состояние совпадает с конечным.
solve(FinalState, FinalState, _, Path) :-
    reverse(Path, Solution), % Переворачиваем Path, т.к он хранится в обратном порядке из-за рекурсии, результат записываем в Solution
    print_solution(Solution). % Вывод Solution в консоль.

solve(CurrentState, FinalState, Visited, Path) :-
    move(CurrentState, NextState, Action), % переход от одного состояния к следующему
    \+ member(NextState, Visited), % проверка, что такое состояние ещё не было достигнуто, предотвращает циклы в поиске решения.
    solve(NextState, FinalState, [NextState | Visited], [Action | Path]). % рекурсивно вызываем для перехода в следующее состояние, добавляем NextState в Visited и Action в Path.

% Вывод решения в консоль. 
print_solution([]). % Остановка вывода, когда Rest будет пустым списком.
print_solution([Step | Rest]) :-
    write(Step), nl, % записываем один шаг и вызываем 
    print_solution(Rest).

% Стартовое положение человека, капусты, козы и волка, запуск поиска и вывода.
start(InitialState, FinalState) :-
    solve(InitialState, FinalState, [InitialState], []). % вызываем поиск решения для текущего начального состояния и конечного, сразу добавляя InitialState в Visited.