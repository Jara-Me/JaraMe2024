import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import { useState } from "react";
import { auth } from "../firebase";
import { useNavigate } from "react-router-dom";
import { FirebaseError } from "firebase/app";
import { Link } from "react-router-dom";
import {
    Error,
    Form,
    Input,
    Switcher,
    Title,
    Wrapper,
    RadioContainer,
    InputWrapper,
} from "../components/auth-components";
import GithubButton from "../components/github-btn";


// 에러에 따라 에러 멘트 출력
const errors = {
    "auth/email-already-in-use":"이미 존재하는 이메일입니다."
}

export default function CreateAccount() {

    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false);
    const [name, setName] = useState("");
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [interest, setInterest] = useState("health");
    const [error, setError] = useState("");


    const onChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        const {target : {name, value}} = e;
        if(name === "name") {
            setName(value);
        } else if (name === "nickname") {
            setNickname(value);
        } else if (name === "email") {
            setEmail(value);
        } else if (name === "password") {
            setPassword(value);
        } else if (name === "confirmPassword") {
            setConfirmPassword(value);
        } else if (name === "interest") {
            setInterest(value);
        } 
    };

    const onSubmit = async (e : React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");

        if(isLoading || nickname === "" || email === "" || password==="") return;

        try {
            setIsLoading(true);
            // create an account
            const credentials = await createUserWithEmailAndPassword(auth, email, password);
            console.log(credentials.user);

            // redirect to the home page
            navigate("/");

        } catch(e) {
            // setError
            if(e instanceof FirebaseError){
                setError(e.message);
            }
        } finally {
            setIsLoading(false);
        }

        console.log(name, nickname, email, password, confirmPassword, interest);
    };

    return <Wrapper>
        <Title>회원가입</Title>
        <Form onSubmit={onSubmit}>
        <InputWrapper><Input onChange = {onChange} name="nickname" value={nickname} placeholder="닉네임" type="text" required/></InputWrapper>
        <InputWrapper><Input onChange = {onChange} name="email" value={email} placeholder="이메일" type="email" required/></InputWrapper>
        <InputWrapper><Input onChange = {onChange} name="password" value={password} placeholder="비밀번호" type="password" required/></InputWrapper>
        <InputWrapper><Input onChange = {onChange} name="confirmPassword" value={confirmPassword} placeholder="비밀번호 확인" type="password" required/></InputWrapper>
            <div style={{"display":"flex", "justifyContent":"space-evenly"}}>
             <RadioContainer style={{ flex: 1 }}><Input onChange = {onChange} type="radio" name="interest" value="health" checked={interest === "health"} id="healthBtn"/><label htmlFor="healthBtn">건강(운동)</label></RadioContainer>
             <RadioContainer style={{ flex: 1 }}><Input onChange = {onChange} type="radio" name="interest" value="study" checked={interest === "study"} id="studyBtn" /><label htmlFor="studyBtn">공부</label></RadioContainer>
             <RadioContainer style={{ flex: 1 }}><Input onChange = {onChange} type="radio" name="interest" value="hobby" checked={interest === "hobby"} id="hobbyBtn"/><label htmlFor="hobbyBtn">취미</label></RadioContainer>
            </div>
        <Input type="submit" value={isLoading ? "Loading..." : "계정 생성"}/>


        </Form>
        {error !== "" ? <Error>{error}</Error> : null}
        <Switcher>
            이미 계정이 있으신가요? {" "}
            <Link to="/login">로그인</Link>
        </Switcher>

        <GithubButton/>
        
    </Wrapper>
} 