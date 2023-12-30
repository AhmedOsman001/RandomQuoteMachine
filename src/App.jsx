import { useEffect, useState } from 'react'
import './App.css'

import React from 'react';

function App() {


  const [color, setColor] = useState("ff92bc")    
  const [allQuotes , setAllQuotes]= useState([])
  const [quote , setQuote]= useState(
    {
      "quote":"Life isn't about getting and having, it's about giving and being.",
      "author":"Kevin Kruse"
    }
  )

  document.body.style.background = `#${color}`
  const backgroundColor = {backgroundColor:`#${color}`}
  const Color = {color:`#${color}`}

  useEffect(()=> {
    fetch('https://dummyjson.com/quote/')
      .then((response) => response.json())
      .then(data => setAllQuotes(data.quotes))
      
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  },[])
  function getQuote() {
    const randomNumber = Math.floor(Math.random() * allQuotes.length)
    setQuote(allQuotes[randomNumber])

    const randomColor = Math.floor(Math.random() * 16777215)
    .toString(16)
    .padStart(6, '0');
    setColor(randomColor)
  }

  let regQuote = quote.quote
  let regAuthor = quote.author
  let regex =  /\s/g
  let replacement = "%20"
  let quoteURL = regQuote.replace(regex,replacement)
  let autherURL = regAuthor.replace(regex,replacement)

  let url = `https://twitter.com/intent/tweet?hashtags=quotes&related=freecodecamp&text="${quoteURL}"%20-${autherURL}`
  return (

    <>
      <div className='quote-box' id='quote-box' style={Color}>

        <div className='quote-text' id='text'>
        <i style={Color} class="fa fa-quote-left" ></i>
        <span style={Color} >{quote.quote}</span>
        </div>

        <div className='quote-author' id='author'>
          - <span style={Color}>{quote.author}</span>  
        </div>

        <div className="buttons">
          <a className='twitter' target="_blank" href={url} style={backgroundColor} >
            <svg  xmlns="http://www.w3.org/2000/svg" height="16" width="16" viewBox="0 0 512 512">
              <path d="M389.2 48h70.6L305.6 224.2 487 464H345L233.7 318.6 106.5 464H35.8L200.7 275.5 26.8 48H172.4L272.9 180.9 389.2 48zM364.4 421.8h39.1L151.1 88h-42L364.4 421.8z"/>
            </svg>
          </a>
          <button className='button' onClick={getQuote} style={{backgroundColor:`#${color}`}}>New Quote</button>
        </div>
        
      </div>
      <div className='footer'>
          by Ahmed
        </div>
    </>
  )
}

export default App
