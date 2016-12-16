import time
import json
import urllib2
import base64
import pprint 

USERNAME = "admin"
PASSWORD = "admin"

# NameNode metrics.
query = "http://kjss1:8080/api/v1/clusters/JoyCluster/services/HDFS/components/NAMENODE"
connection_timeout = 30.0

def get_json_beans(query, connection_timeout):
  	response = None
  	try:
		raw = "%s:%s" % (USERNAME, PASSWORD)
		auth = 'Basic %s' % base64.b64encode(raw).strip()
		request = urllib2.Request(query)
		request.add_unredirected_header('Authorization', auth)  		
		response = urllib2.urlopen(request, timeout=connection_timeout)
		data = response.read()
		data_dict = json.loads(data)
		return data_dict
	finally:
		if response is not None:
			try:
				response.close()
			except:
				pass


def print_dictionary(d, indent=0):
	for key, value in d.iteritems():
		print '\t' * indent + str(key),
		if isinstance(value, dict):
			print ''
			print_dictionary(value, indent+1)
		else:
			print ('\t' * (indent+1) + str(value))

json_data = get_json_beans(query, connection_timeout)

print '------------------ DFS metrics ------------------'
dfs_metrics = json_data['metrics']['dfs']
print_dictionary(dfs_metrics, 1)

print '------------------ JVM metrics ------------------'
jvm_metrics = json_data['metrics']['jvm']
print_dictionary(jvm_metrics, 1)
