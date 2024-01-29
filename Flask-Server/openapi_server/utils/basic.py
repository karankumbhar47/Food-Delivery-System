import secrets
import re
import bcrypt
import base64


def generate_uid(length: int = 40):
    characters = "abcdefg"
    return ''.join(secrets.choice(characters) for _ in range(length))


def username_is_valid(username: str) -> bool:
    if len(username) < 4 or ' ' in username:
        return False
    return True


def phone_is_valid(phone: int) -> bool:
    str_phone = str(phone)
    if len(str_phone) == 10 and str_phone[0] != 0 : 
        return True
    return False


def email_is_valid(email: str) -> bool:
    pattern = re.compile(r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')
    return bool(re.fullmatch(pattern, email)) 


def password_is_weak(password: str) -> bool:
    flag = 0 
    while True : 
        if len(password) < 8 :
            flag = -1 
            break
        elif not re.search("[a-z]", password):
            flag = -1
            break
        elif not re.search("[A-Z]", password):
            flag = -1
            break
        elif not re.search("[0-9]", password):
            flag = -1
            break
        elif not re.search(r'''[!@#$%^&*()_+[\]\{}|;':",./<>?`~]''', password):
            flag = -1
            break
        else:
            flag = 0
            break

    if flag == -1 :
        return True
    return False


def hash_password(password: str) -> str:
    hash_pwd = bcrypt.hashpw(password.encode('utf-8'),
                             bcrypt.gensalt())
    return base64.b64encode(hash_pwd).decode('utf-8')


def verify_password(pwd: str, pwd_hash: str) -> bool:
    return bcrypt.checkpw(pwd.encode('utf-8'), base64.b64decode(pwd_hash))
