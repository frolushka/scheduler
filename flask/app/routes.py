# -*- coding: utf-8 -*-
from app import app
from flask import render_template
from flask import request
from flask import redirect
from flask import url_for
import json

app.debug = True

@app.route('/')
@app.route('/index')
def index():
	username = request.args.get('username')
	if(username == None):
		redirect(url_for('login'))
	login = request.args.get('login')
	title = u'sss'
	return render_template('index.html', username=username, login=login, title=title)
	
@app.route('/login')
def login():
	return render_template('login.html')

@app.route('/get_data', methods=['GET'])
def get_data():
	s = str(request.args.get('login'))
	f = open('data.json', 'r')
	s = f.read().decode('utf8')
	f.close()
	# u = json.loads(s)

	return s

@app.route('/refresh_data', methods=['POST'])
def refresh_data():
	jsdata = request.form.get('str')
	f = open('data.json', 'w')
	f.write(jsdata.encode('utf8'))
	f.close()
	return 'success'

@app.route('/correct_login', methods=['POST','GET'])
def correct_login():
	login = str(request.args.get('login'))
	password = str(request.args.get('password'))
	f = open('database.json','r')
	s = f.read().decode('utf-8')
	u = json.loads(s)
	for i in u:
		if(i['login'] == login):
			if(i['password'] == password):
				q = i['name'].encode('utf-8')
				r = i['login'].encode('utf-8')
				return url_for('index',username=q,login=r)
		else:
			return 'error'
	return 'error'

@app.route('/registration')
def registration():
	return render_template('registration.html')

@app.route('/correct_reg')
def correct_reg():
	login = str(request.args.get('login'))
	password = str(request.args.get('password'))
	name = request.args.get('name').encode('utf-8')
	f = open('database.json','r')
	s = f.read().decode('utf-8')
	u = json.loads(s)
	for i in u:
		if(i['login'] == login):
			return 'error'

	u.append({"login":login,"password":password,"name":name})
	s = json.dumps(u)
	f.close()
	f = open('database.json','w')
	f.write(s)
	f.close()
	f = open(u'app\\static\\events\\' + login + u'.json','w')
	f.close()
	return url_for('index',username=name,login=login)
