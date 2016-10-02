# Copyright 2013 Google Inc. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#         http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""
Sample App Engine application demonstrating how to connect to Google Cloud SQL
using App Engine's native unix socket.

For more information, see the README.md.
"""

# [START all]

import os

import MySQLdb
import webapp2
import logging

CLOUDSQL_PROJECT = 'grounded-plane-144407'
CLOUDSQL_INSTANCE = 'us-central1:marvel'


class MainPage(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'

        # When running on Google App Engine, use the special unix socket
        # to connect to Cloud SQL.
        if os.getenv('SERVER_SOFTWARE', '').startswith('Google App Engine/'):
            db = MySQLdb.connect(
                unix_socket='/cloudsql/{}:{}'.format(
                    CLOUDSQL_PROJECT,
                    CLOUDSQL_INSTANCE),
                user='root', passwd='Teracon11$')
        # When running locally, you can either connect to a local running
        # MySQL instance, or connect to your Cloud SQL instance over TCP.
        else:
            db = MySQLdb.connect(host='localhost', user='root')

        cursor = db.cursor()
	cursor.execute('use marvel')
        cursor.execute('select * from employee')

        for r in cursor.fetchall():
            self.response.write('{}\n'.format(r))

class WriteDB(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
        self.response.write('Writing Tables')
    def post(self):
	emp_id = self.request.get('eid')
	emp_name = self.request.get('ename')
	emp_address = "Test Address"
	if os.getenv('SERVER_SOFTWARE', '').startswith('Google App Engine/'):
            db = MySQLdb.connect(
                unix_socket='/cloudsql/{}:{}'.format(
                    CLOUDSQL_PROJECT,
                    CLOUDSQL_INSTANCE),
                user='root', passwd='Teracon11$')
        # When running locally, you can either connect to a local running
        # MySQL instance, or connect to your Cloud SQL instance over TCP.
#        else:
#            db = MySQLdb.connect(host='localhost', user='root')

	cursor = db.cursor()
	cursor.execute('use marvel;')
	query = 'insert into employee values(%s,\"%s\",\"%s\");'%(emp_id,emp_name,emp_address)
        cursor.execute(query)
	db.commit()

	self.response.headers['Content-Type'] = 'text/plain'
	self.response.write('Post in Writing Tables %s'%(query))

class ReadDB(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
	self.response.write('Reading tables')



app = webapp2.WSGIApplication([
    ('/', MainPage),
    (r'/write', WriteDB),
    (r'/read', ReadDB),
], debug=True)

# [END all]
