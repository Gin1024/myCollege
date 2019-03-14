// syntax.cpp: 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <string>
#include <cstring>
#include <cmath>
#include <stack>
#include <vector>
#include <string>
#include <set>
#include <algorithm>
#include <map>

#define MAX 100

using namespace std;

struct production //产生式的结构
{
	char left;
	string right;
};


class Pre
{
protected:
	int T;
	production analysis[MAX]; //输入文法分析
	set<char> FIRST[MAX];//First集
	set<char> FOLLOW[MAX];//Follow集
	vector<char> terminal_NoEmpty; //去$终结符
	vector<char> terminal;//终结符
	vector<char> nonterminal;//非终结符

public:
	Pre() :T(0) {}
	
	//获得在终结符集合中的下标
	bool isNotSymbol(char c)
	{
		if (c >= 'A' && c <= 'Z')
			return true;
		return false;
	}

	int get_index(char set)
	{
		for (int i = 0; i < nonterminal.size(); i++) {
			if (set == nonterminal[i])
				return i;
		}
		return -1;
	}

	//获得在非终结符集合中的下标
	int get_nindex(char set)
	{
		for (int i = 0; i < terminal_NoEmpty.size(); i++) {
			if (set == terminal_NoEmpty[i])
				return i;
		}
		return -1;
	}

	//得到First集合
	void get_first(char Set)
	{
		int flag = 0;
		int tag = 0;
		for (int i = 0; i < T; i++) {
			if (analysis[i].left == Set) {//产生式左部匹配
				if (!isNotSymbol(analysis[i].right[0])) {//终结符直接加入First
					FIRST[get_index(Set)].insert(analysis[i].right[0]);
				}
				else {
					for (int j = 0; j < analysis[i].right.length(); j++) {
						if (!isNotSymbol(analysis[i].right[j])) {//终结符直接加入First
							FIRST[get_index(Set)].insert(analysis[i].right[j]);
							break;
						}
						//cout << analysis[i].right[j] << endl; //输出测试用
						get_first(analysis[i].right[j]);

						set<char>::iterator temp;
						for (temp = FIRST[get_index(analysis[i].right[j])].begin();
							temp != FIRST[get_index(analysis[i].right[j])].end(); temp++) {
							if (*temp == '$')
								flag = 1;
							else
								FIRST[get_index(Set)].insert(*temp); //First(Y)中的非$终结符加入First(X)
						}
						if (flag == 0)
							break;
						else {
							tag += flag;
							flag = 0;
						}
					}
					if (tag == analysis[i].right.length())
						FIRST[get_index(Set)].insert('$'); //所有右部First(Y)都有$，将$加入First
				}
			}
		}
	}

	//得到Follow集合
	void get_follow(char Set)
	{
		for (int i = 0; i < T; i++) {
			int index = -1;
			int length = analysis[i].right.length();
			for (int j = 0; j < length; j++) {
				if (analysis[i].right[j] == Set) { //找出该产生式下标
					index = j;
					break;
				}
			}
			if (index != -1 && index < length - 1) {
				char next = analysis[i].right[index + 1];

				if (!isNotSymbol(next))
					FOLLOW[get_index(Set)].insert(next);
				else {
					int temp = 0;
					set<char>::iterator it;
					for (it = FIRST[get_index(next)].begin(); it != FIRST[get_index(next)].end(); it++) {
						if (*it == '$')
							temp = 1;
						else
							FOLLOW[get_index(Set)].insert(*it);
					}
					if (temp && analysis[i].left != Set) {
						get_follow(analysis[i].left);//递归
						char ch = analysis[i].left;
						set<char>::iterator it;
						for (it = FOLLOW[get_index(ch)].begin(); it != FOLLOW[get_index(ch)].end(); it++) {
							FOLLOW[get_index(Set)].insert(*it);
						}
					}
				}
			}
			else if (index != -1 && index == length - 1 && Set != analysis[i].left) {
				get_follow(analysis[i].left);//递归
				char ch = analysis[i].left;
				set<char>::iterator it;
				for (it = FOLLOW[get_index(ch)].begin(); it != FOLLOW[get_index(ch)].end(); it++) {
					FOLLOW[get_index(Set)].insert(*it);
				}
			}
		}
	}

	//处理得到First和Follow集合
	void get_result()
	{
		cout << endl;
		cout << endl;
		cout << "YOU CAN ONLY USE THE PREPROCESSED-PRODUCTIONS!!!" << endl;
		cout << endl;
		cout << endl;
		cout << "Please input the number of productions: ";
		cin >> T;
		string s;
		cout << "enter the productions(use $ to replace ε): " << endl;
		for (int temp = 0; temp < T; temp++) {
			cin >> s;
			string t = "";
			for (int i = 0; i < s.length(); i++) {
				if (s[i] != ' ')
					t += s[i];
			}
			analysis[temp].left = t[0];

			for (int j = 3; j < t.length(); j++)
				analysis[temp].right += t[j];

			for (int j = 0; j < t.length(); j++) {
				if (t[j] != '-'&&t[j] != '>') {
					if (isNotSymbol(t[j])) {
						int flag = 0;
						for (int a = 0; a < nonterminal.size(); a++) {
							if (nonterminal[a] == t[j]) {
								flag = 1;
								break;
							}
						}
						if (!flag)
							nonterminal.push_back(t[j]);
					}
					else {
						int flag = 0;
						for (int a = 0; a < terminal.size(); a++) {
							if (terminal[a] == t[j])
							{
								flag = 1;
								break;
							}
						}
						if (!flag)
							terminal.push_back(t[j]);
					}
				}
			}
		}
		terminal.push_back('#');

		for (int i = 0; i < nonterminal.size(); i++)
		{
			get_first(nonterminal[i]);
		}

		for (int i = 0; i < nonterminal.size(); i++)
		{
			if (i == 0)
				FOLLOW[0].insert('#');
			get_follow(nonterminal[i]);
		}

		for (int i = 0; i < terminal.size(); i++)
		{
			if (terminal[i] != '$')
				terminal_NoEmpty.push_back(terminal[i]);
		}
	} 

	//输出First和Follow集合
	void displayFF()
	{
		cout << "FIRST集合为" << endl;
		for (int i = 0; i < nonterminal.size(); i++)
		{
			cout << nonterminal[i] << ": ";
			set<char>::iterator it;
			for (it = FIRST[i].begin(); it != FIRST[i].end(); it++)
				cout << *it << "  ";
			cout << endl;
		}

		cout << "FOLLOW集合为" << endl;
		for (int i = 0; i < nonterminal.size(); i++)
		{
			cout << nonterminal[i] << ": ";
			set<char>::iterator it;
			for (it = FOLLOW[i].begin(); it != FOLLOW[i].end(); it++)
				cout << *it << "  ";
			cout << endl;
		}
	}  

};

class TableStack :public Pre
{
protected:
	vector<char> analysis_stack; //分析栈
	vector<char> left_analysis;//剩余输入串
	int PPT[100][100];//预测表
public:
	TableStack() {
		memset(PPT, -1, sizeof(PPT));
	}

	//得到预测表
	void get_PPT()
	{
		for (int i = 0; i < T; i++)
		{
			char ch = analysis[i].right[0];
			if (!isNotSymbol(ch))
			{
				if (ch != '$')
					PPT[get_index(analysis[i].left)][get_nindex(ch)] = i;
				if (ch == '$')
				{
					set<char>::iterator it;
					for (it = FOLLOW[get_index(analysis[i].left)].begin(); it != FOLLOW[get_index(analysis[i].left)].end(); it++)
					{
						PPT[get_index(analysis[i].left)][get_nindex(*it)] = i;
					}
				}
			}
			else
			{
				set<char>::iterator it;
				for (it = FIRST[get_index(ch)].begin(); it != FIRST[get_index(ch)].end(); it++)
				{
					PPT[get_index(analysis[i].left)][get_nindex(*it)] = i;
				}
				if (FIRST[get_index(ch)].count('$') != 0)
				{
					set<char>::iterator s;
					for (s = FOLLOW[get_index(analysis[i].left)].begin(); s != FOLLOW[get_index(analysis[i].left)].end(); s++)
					{
						PPT[get_index(analysis[i].left)][get_nindex(*s)] = i;
					}
				}
			}
		}
	} 
	
	//分析栈的处理
	void analyExp(string s)
	{
		for (int i = s.length() - 1; i >= 0; i--)
			left_analysis.push_back(s[i]);

		analysis_stack.push_back('#');
		analysis_stack.push_back(nonterminal[0]);

		while (left_analysis.size() > 0)
		{
			//分析栈
			string outs = "";
			for (int i = 0; i < analysis_stack.size(); i++)
				outs += analysis_stack[i];
			cout << setw(15) << outs;

			//剩余输入串
			outs = "";
			for (int i = left_analysis.size() - 1; i >= 0; i--)
				outs += left_analysis[i];
			cout << setw(15) << outs;

			char char1 = analysis_stack[analysis_stack.size() - 1];
			char char2 = left_analysis[left_analysis.size() - 1];
			if (char1 == char2 && char1 == '#') {
				cout << setw(15) << "Accepted!" << endl;
				return;
			}

			if (char1 == char2) {
				analysis_stack.pop_back();
				left_analysis.pop_back();
				cout << setw(15) << char1 << "match " << endl;
			}

			else if (PPT[get_index(char1)][get_nindex(char2)] != -1) {
				int temp = PPT[get_index(char1)][get_nindex(char2)];
				analysis_stack.pop_back();

				if (analysis[temp].right != "$") {
					for (int i = analysis[temp].right.length() - 1; i >= 0; i--)
						analysis_stack.push_back(analysis[temp].right[i]);
				}

				cout << setw(15) << analysis[temp].right << endl;
			}
			else {
				cout << setw(15) << "Error!" << endl;
				return;
			}
		}
	} 
	
	//输出
	void print()
	{
		for (int i = 0; i < terminal_NoEmpty.size(); i++)
		{
			cout << setw(10) << terminal_NoEmpty[i];
		}
		cout << endl;
		for (int i = 0; i < nonterminal.size(); i++)
		{
			cout << nonterminal[i] << ": ";
			for (int j = 0; j < terminal_NoEmpty.size(); j++)
			{
				if (PPT[i][j] == -1)
					cout << setw(10) << "error";
				else
					cout << setw(10) << analysis[PPT[i][j]].right;

			}
			cout << endl;
		}
	}
	
	//结合处理
	void getAns()
	{
		get_result();
		displayFF();
		get_PPT();
		print();

		string ss;
		cout << "请输入符号串（#代表$R）：" << endl;
		cin >> ss;
		cout << setw(15) << "分析栈" << setw(15) << "剩余输入串" << setw(15) << "推导式" << endl;
		analyExp(ss);
	} 
};

int main()
{
	TableStack t;
	t.getAns();
	system("pause");
	return 0;
}

