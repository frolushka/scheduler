# -*- coding: utf-8 -*-
from app import app
from flask import render_template
from flask import request
import json

@app.route('/')
@app.route('/index')
def index():
	user = u'Олег Григорьев'
	title = u'Календарь'
	return render_template('index.html', username=user, title=title)
	
@app.route('/login')
def login():
	return render_template('login.html')

@app.route('/get_data', methods=['GET'])
def get_data():
	f = open('data.json', 'r')
	s = f.read().decode('utf8')
	f.close()
	return s

@app.route('/refresh_data', methods=['POST'])
def refresh_data():
	jsdata = request.form.get('str')
	f = open('data.json', 'w')
	f.write(jsdata.encode('utf8'))
	f.close()
	return 'success'