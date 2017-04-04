/**
 * Creates pre-filled demo account
 */

print('dump start');

db.users.update(
    { "_id": "tcthien" },
    {
	    "_id": "tcthien",
	    "password": "$2a$10$vPBKiz7Jo9n/VRDr0ZaHTe.y6CaxTlhLTTxhIMYLXSSjnuj8978Ra"/*tcthien*/,
	    "role" : "ADMIN",
	    "fullName": "Thien Tran",
	    "email": "thientran1986@gmail.com"
    },
    { upsert: true }
);

print('dump complete');