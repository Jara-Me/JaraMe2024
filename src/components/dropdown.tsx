import { useState } from "react";
import styled from "styled-components";
import { palette } from "../assets/styles/palette";

const UL = styled.ul`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    line-height: 180%;
    margin-bottom: 50px;
`;

const LI = styled.li<{$isActive:boolean|undefined}>`
    cursor: pointer;
    color: ${(props) => (props.$isActive ? "black" : "gray")};
    font-weight: ${(props) => (props.$isActive ? "bold" : "normal")};   

`;

const ULTitle = styled.div`
    border: 1px solid ${palette.jarameGrey};
    border-radius: 40px;
    padding: 13px;
    font-weight: bold;
    white-space: nowrap;
    cursor: pointer;
`;


const DropdownMenu: React.FC<{ onGroupSelect: (group: string | null) => void }> = ({ onGroupSelect }) => {
    const groups = ["C를 씹어먹자", "거북목탈퇴클럽", "실리콘드림"];
    const [activeGroup, setActiveGroup] = useState("");

    return (
        <>
            <LI $isActive={activeGroup === ""} onClick={() => { setActiveGroup(""); onGroupSelect(null); }}>전체</LI>
            {groups.map((item, index) => (
                <LI
                    key={index}
                    $isActive={activeGroup === item}
                    onClick={() => { setActiveGroup(item); onGroupSelect(item); }}
                >
                    {item}
                </LI>
            ))}
        </>
    );
};

function Dropdown({ onGroupSelect }: { onGroupSelect: (group: string | null) => void }) {
    const [view, setView] = useState(false);

    return (
        <UL>
            <ULTitle onClick={() => setView(!view)}>그룹 선택{" "}{view ? '▲' : '▼'}</ULTitle>
            {view && <DropdownMenu onGroupSelect={onGroupSelect} />}
        </UL>
    );
}

export default Dropdown;