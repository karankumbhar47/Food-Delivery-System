import secrets


def generate_uid(length: int = 40):
    characters = "abcdefg"
    return ''.join(secrets.choice(characters) for _ in range(length))


def username_exists(username: str) -> bool:
    return False


def phone_is_valid(phone: int) -> bool:
    return True


def email_is_valid(email: str) -> bool:
    return True


def password_is_weak(password: str) -> bool:
    return False
