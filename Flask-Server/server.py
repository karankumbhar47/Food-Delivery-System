from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route('/')
def badReq():
    return "Bad Request", 400

@app.route('/register', methods=["POST"])
def registerReq():
    contentType = request.headers.get("Content-Type")
    if (contentType == "application/json"):
        try:
            user   = request.json['username']
            passwd = request.json['password']
            name   = request.json['name']
            phone  = request.json['phone']
            email  = request.json['email']
            gender = request.json['gender']
        except Exception as e:
            print(e)
            return "Bad Request", 400
        if (user == "valid"):
            return jsonify({"user_uniq": True, "pass_val": True}), 200
        else:
            return jsonify({"user_uniq": False, "pass_val": True}), 403
