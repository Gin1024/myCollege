// lexical.cpp: 定义控制台应用程序的入口点。
//

#include"stdafx.h"
#include<iostream>
using namespace std;
#define MAX 26

/*  Classification：
keyword----1
operator----2
delimiter----3
id----4
num----5
No-recognition----6
*/

char ch = ' ';
char token[100];//定义获取的字符

				//定义keyword
const char* keyWord[] = {
	"include","iostream","using","namespace","std""if","else","switch","case","break","for","while",
	"do","continue","true","false","const","auto","double","int","float","struct","long","char","main",
	"return","define","void","abstract","static","MAX","print","unsigned","short","class","system" };


//判断是否为关键字
bool isKey(char * token)
{
	for (int i = 0; i < MAX; i++)
	{
		if (strcmp(token, keyWord[i]) == 0)
			return true;
	}
	return false;
}

//判断是否是数字
bool isDigit(char digit)
{
	if (digit >= '0' && digit <= '9')
		return true;
	else
		return false;
}

//判断是否是字母
bool isLetter(char letter)
{
	if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'))
		return true;
	else
		return false;
}


//词法分析
int Lexical_Analyze(FILE *input, FILE *output)
{
	while ((ch = fgetc(input)) != EOF) {

		//语句间的分隔符
		if (ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r') {
			//ch = getc(input);
		}

		else if (isLetter(ch)) {
			char token[100] = { '\0' };
			int i = 0;

			while (isLetter(ch) || isDigit(ch)) {
				token[i] = ch;
				i++;
				ch = fgetc(input);
			}

			fseek(input, -1L, SEEK_CUR);

			if (isKey(token)) {
				fprintf(output, "%s\t\t%u\t%s\n", token, 1, "<keyword>");
			}
			else {
				fprintf(output, "%s\t\t%u\t%s\n", token, 4, "<id>");
			}
		}

		else if (isDigit(ch) || (ch == '.'))
		{
			int i = 0;
			char token[100] = { '\0' };

			while (isDigit(ch) || (ch == '.' && isDigit(fgetc(input)))) {
				if (ch == '.')
					fseek(input, -1L, SEEK_CUR);
				token[i] = ch;
				i++;
				ch = fgetc(input);
			}
			fseek(input, -1L, SEEK_CUR);
			//属于无符号数字
			fprintf(output, "%s\t\t%u\t%s\n", token, 5, "<num>");
		}

		else switch (ch) {
		case '+': {
			ch = fgetc(input);
			if (ch == '+')
				fprintf(output, "%c%c\t\t%u\t%s\n", '+', ch, 2, "<operator>");
			else if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", '+', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '+', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case '-': {
			ch = fgetc(input);
			if (ch == '-')
				fprintf(output, "%c%c\t\t%u\t%s\n", '-', ch, 2, "<operator>");
			else if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", '-', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '-', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case '*': {
			ch = fgetc(input);
			if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", '*', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '*', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case '/': {
			ch = fgetc(input);
			if (ch == '/') //注释符
				fprintf(output, "%c%c\t\t%u\t%s\n", '/', ch, 2, "<delimiter>");
			else if (ch == '=')
				fprintf(output, "%c\t\t%u\t%s\n", '/', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '/', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case '(':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 2, "<operator>");
			break;
		case ')':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 2, "<operator>");
			break;
		case '[':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 2, "<operator>");
			break;
		case ']':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 2, "<operator>");
			break;
		case '{':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 2, "<operator>");
			break;
		case '}':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 2, "<operator>");
			break;
		case '#':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 3, "<delimiter>");
			break;
		case ',':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 3, "<delimiter>");
			break;
		case '"':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 3, "<delimiter>");
			break;
		case '\'':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 3, "<delimiter>");
			break;
		case ';':
			fprintf(output, "%c\t\t%u\t%s\n", ch, 3, "<delimiter>");
			break;

		case '=': {
			ch = fgetc(input);
			if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", '=', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '=', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case ':': {
			ch = fgetc(input);
			if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", ':', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", ':', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case '>': {
			ch = fgetc(input);
			if (ch == '>')
				fprintf(output, "%c%c\t\t%u\t%s\n", '>', ch, 3, "<delimiter>");
			if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", '>', ch, 2, "<operator>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '>', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

		case '<': {
			ch = fgetc(input);
			if (ch == '=')
				fprintf(output, "%c%c\t\t%u\t%s\n", '<', ch, 2, "<operator>");
			if (ch == '<')
				fprintf(output, "%c%c\t\t%u\t%s\n", '<', ch, 3, "<delimiter>");
			else {
				fprintf(output, "%c\t\t%u\t%s\n", '<', 2, "<operator>");
				fseek(input, -1L, SEEK_CUR);
			}
		}break;

			//无识别
		default:
			fprintf(output, "%c\t\t%u\t%s\n", ch, 6, "<No-recognition >");
		}
	}

	return 1;
}

int main() {
	char input[30];
	FILE *fin, *fout;

	fin = fopen("..\\bin\\in.txt", "r");
	if (fin == NULL) {
		cout << "The input file is not exist!" << endl;
		return 0;
	}

	fout = fopen("..\\bin\\out.txt", "w");
	if (fout == NULL) {
		cout << "The output file is not exist!" << endl;
		return 0;
	}

	if (Lexical_Analyze(fin, fout) == 1) {
		cout << "Lexical analyze succeeds!" << endl;
	}
	else
		cout << "Lexical analyze fails..." << endl;

	fclose(fin);
	fclose(fout);

	system("..\\bin\\out.txt");

	system("pause");
	return 0;
}

