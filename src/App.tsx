import React, { useState, useRef, useEffect } from 'react';
import styled from 'styled-components';
import { Routes, Route, BrowserRouter, Link, Router } from "react-router-dom";
import Main from './routes/main';
import Mong from './components/mong';
import SearchContent from './components/Search';
import ProfileContent from './components/Profile';
import MenuButton from './components/MenuButton';
import TodayContent from './components/Today';
import JarausContent from './components/Jaraus';
import Calendar from './components/Calender';
import MyJaraus from './routes/MyJaraus';
import SearchDetail from './routes/SearchDetail';

function App() {
  return (

      <BrowserRouter>
      <Routes>
            <Route path='/' element={<Main />} />
            <Route path='/my' element={<MyJaraus />} />
            <Route path='/sd' element={<SearchDetail />} />
      </Routes>
      </BrowserRouter>
  );
}


export default App;
