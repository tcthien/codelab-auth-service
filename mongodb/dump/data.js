/**
 * Creates pre-filled demo account
 */

print('dump start');

db.users.update(
    { "_id": "1" },
    {
	    "_id": "1",
	    "password": "",
	    "fullName": "Thien Tran",
	    "email": "thientran1986@gmail.com"
    },
    { upsert: true }
);

print('dump complete');