# Database Layout

We're using MongoDB atlas. MongoDB is a document database, so instead of SQL tables, your database looks like a giant JSON document.
Objects/Dictionaries/Maps/Hashmaps/Hashes are called *Documents*, and Arrays/Lists are called *Collections*.

The database is called `Db1`, contained in the deployment `StudySearchA1`, contained in the project `StdyDotTech`.
The database contains two collections:
- `Users`, which contains users and their information.
  - Each document has a format described in the "User Format" section.
- `Groups`, which contains the automatically generated groups.
  - Each document has a format described in the "Group Format" section.

### User Format

```json
{
	"uid": 1234,
	"info": {
		"profile": {
			"screen_name": "mrbean6000",
			"name_name": "Mr Bean",
			"phone": "+1 (608) 257-9248",
			"email": "atkinson@wisc.edu"
		},
		"availability": {
			"every_week": {
				"Mon": [
					{"start": "<time>", "end": "<time>"}
				],
			},
		},
		"classes": {
			"class_list": [
				{
					"name": "PHYSICS 535",
					"long_name": "Introduction to Particle Physics",
					"times_every_week": {
						"Fri": [
							{"start": "<time>", "end": "<time>"}
						]
					}
				}
			]
		},
		"other": {
			"prefer_same_section": true,
			"desired_headcount_min": 3,
			"desired_headcount_max": 6
		},
	},
}
```

### Group Format

```
{
	"gid": 343434,
	"member_uids": [
		1234,
		5678,
		9810
	]
}
```

